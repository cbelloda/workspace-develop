package com.inetum.evaluaciones.ibk.expose.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

  private String number;

  private Currency currency;

  public enum Currency {
    PEN, USD
  }
  
}
