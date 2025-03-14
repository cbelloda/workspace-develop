package com.inetum.evaluaciones.ibk.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Account {

    @Id
    private Long id;

    private final String numberAccount;

    private final Boolean state;

    private final Long idUser;

    private final String currency;
  
}
