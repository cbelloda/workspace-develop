package com.inetum.evaluaciones.ibk.webclient.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
  private Long id;
  private String name;
  private Boolean state;
}
