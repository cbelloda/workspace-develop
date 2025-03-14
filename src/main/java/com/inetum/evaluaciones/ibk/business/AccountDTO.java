package com.inetum.evaluaciones.ibk.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

  private Long idUser;
  private Long idAccount;

}
