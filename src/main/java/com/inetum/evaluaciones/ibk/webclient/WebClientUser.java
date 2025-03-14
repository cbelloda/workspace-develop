package com.inetum.evaluaciones.ibk.webclient;

import com.inetum.evaluaciones.ibk.webclient.response.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WebClientUser {
  
  public Mono<User> getUserFromApi(Long id){
    return Mono.empty();
  } 

}
