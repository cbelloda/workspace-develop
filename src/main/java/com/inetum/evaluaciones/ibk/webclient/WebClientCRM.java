package com.inetum.evaluaciones.ibk.webclient;

import com.inetum.evaluaciones.ibk.repository.Account;
import com.inetum.evaluaciones.ibk.webclient.response.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class WebClientCRM {

  public Mono<Void> saveIssue(Long idUser, Long requestCRM){

    return Mono.empty();

  }

  public Mono<Void> saveIssueAccountUpdate(User user, Account account){

    return Mono.empty();

  }

  
}
