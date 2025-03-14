package com.inetum.evaluaciones.ibk.business;

import com.inetum.evaluaciones.ibk.business.exception.BusinessException;
import com.inetum.evaluaciones.ibk.expose.request.AccountRequest;
import com.inetum.evaluaciones.ibk.repository.Account;
import com.inetum.evaluaciones.ibk.repository.AccountRepository;
import com.inetum.evaluaciones.ibk.webclient.WebClientCRM;
import com.inetum.evaluaciones.ibk.webclient.WebClientUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UC1_Accounts_Register {

  private final AccountRepository accountRepository;

  private final WebClientUser webClientUser;

  private final WebClientCRM webClientCRM;

  public Mono<AccountDTO> registerAccount(Long idUser, AccountRequest accountRequest) {



    return Mono.empty();
  }
  

  private Mono<Boolean> isAccountValid(Long id, String currency) {
    return Mono.empty();
  }

  // you can use this method to check if the account has another currency
  private boolean accountWithAnotheCurrency(String currency, Account account) {
    if(account.getCurrency().equals(currency)){
      throw new BusinessException("User have account with same currency : " + currency);
    } 
    return true;
  }


}
