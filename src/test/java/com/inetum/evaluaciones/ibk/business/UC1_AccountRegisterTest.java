package com.inetum.evaluaciones.ibk.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.inetum.evaluaciones.ibk.expose.request.AccountRequest;
import com.inetum.evaluaciones.ibk.expose.request.AccountRequest.Currency;
import com.inetum.evaluaciones.ibk.repository.Account;
import com.inetum.evaluaciones.ibk.repository.AccountRepository;
import com.inetum.evaluaciones.ibk.webclient.WebClientCRM;
import com.inetum.evaluaciones.ibk.webclient.WebClientUser;
import com.inetum.evaluaciones.ibk.webclient.response.User;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

@ExtendWith(MockitoExtension.class)
class UC1_AccountRegisterTest {

  @Mock
  AccountRepository accountRepository;

  @Mock
  WebClientUser webClientUser;

  @Mock
  WebClientCRM webClientCRM;

  @InjectMocks
  UC1_Accounts_Register uc1_accounts_register;

  @Test
  @DisplayName("Test Case 1: Fail --> User don't exist")
  void shouldFailUserDontExist() {

    when(webClientUser.getUserFromApi(1L)).thenReturn(Mono.empty());

    Mono<AccountDTO> accountResponse = uc1_accounts_register.registerAccount(1L,
        AccountRequest.builder().number("123456789")
        .currency(Currency.USD)
        .build());

    StepVerifier
        .create(accountResponse)
        .expectError()
        .verify();

  }

  @Test
  @DisplayName("Test Case 2: Fail --> User have account don't permitted exist")
  void shouldFailUserHaveAccountDontPermittedExist() {

    User user = User.builder().id(1L).name("User").build();
    when(webClientUser.getUserFromApi(1L)).thenReturn(Mono.just(user));

    List<Account> accounts = List.of(
        Account.builder().numberAccount("123456788").state(true).currency("USD").idUser(1L).build(),
        Account.builder().numberAccount("987654324").state(false).currency("PEN").idUser(1L).build());

    when(accountRepository.findByUserIdAndState(1L, true)).thenReturn(Flux.just(accounts.get(0)));

    Mono<AccountDTO> accountResponse = uc1_accounts_register
        .registerAccount(1L,
            AccountRequest.builder()
                .number("123456789")
                .currency(Currency.USD)
                .build());

    StepVerifier
        .create(accountResponse)
        .expectError()
        .verify();

  }

  @Test
  @DisplayName("Test Case 3: Success --> all CRM Service and register account")
  void shouldWaitForRegisterCRMAndRegister() {

    User user = User.builder().id(1L).name("User").build();
    when(webClientUser.getUserFromApi(1L)).thenReturn(Mono.just(user));

    List<Account> accounts = List.of(
        Account.builder().numberAccount("123456788").state(true).currency("USD").idUser(1L).build(),
        Account.builder().id(10L).numberAccount("987654324").state(false).currency("PEN").idUser(1L).build());

    when(accountRepository.findByUserIdAndState(1L, true)).thenReturn(Flux.just(accounts.get(0)));
    when(accountRepository.save(any())).thenReturn(Mono.just(accounts.get(1)));
    when(webClientCRM.saveIssue(1L,1L)).thenReturn(Mono.delay(Duration.ofSeconds(3)).then());
    VirtualTimeScheduler.getOrSet();

    Mono<AccountDTO> accountResponse = uc1_accounts_register.registerAccount(1L,
        AccountRequest.builder()
            .number("123456789")
            .currency(Currency.PEN)
            .build());

    StepVerifier.withVirtualTime(() -> accountResponse)
        .expectSubscription()            // Expect the subscription to start
        .expectNoEvent(Duration.ofSeconds(3)) 
        .expectNextMatches(accountDto -> accountDto.getIdAccount().equals(10L) && 
        accountDto.getIdUser().equals(1L) )
        .verifyComplete();

  }

  @Test
  @DisplayName("Test Case 4: Success --> Register dont have any account")
  void shouldRegisterbecauseDontHaveAnyAccount() {

    User user = User.builder().id(1L).name("User").build();
    when(webClientUser.getUserFromApi(1L)).thenReturn(Mono.just(user));

    List<Account> accounts = List.of(
        Account.builder().numberAccount("123456788").state(true).currency("USD").idUser(1L).build(),
        Account.builder().id(10L).numberAccount("987654324").state(false).currency("PEN").idUser(1L).build());

    when(accountRepository.findByUserIdAndState(1L, true)).thenReturn(Flux.empty());
    when(accountRepository.save(any())).thenReturn(Mono.just(accounts.get(1)));
    when(webClientCRM.saveIssue(1L,1L)).thenReturn(Mono.delay(Duration.ofSeconds(3)).then());
    VirtualTimeScheduler.getOrSet();

    Mono<AccountDTO> accountResponse = uc1_accounts_register.registerAccount(1L,
        AccountRequest.builder()
            .number("123456789")
            .currency(Currency.PEN)
            .build());

    StepVerifier.withVirtualTime(() -> accountResponse)
        .expectSubscription()            // Expect the subscription to start
        .expectNoEvent(Duration.ofSeconds(3)) 
        .expectNextMatches(accountDto -> accountDto.getIdAccount().equals(10L) && 
        accountDto.getIdUser().equals(1L) )
        .verifyComplete();

  }
}
