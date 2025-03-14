package com.inetum.evaluaciones.ibk.repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account,Long> {
   Flux<Account> findByUserIdAndState(Long userId, Boolean state);  
   
}
