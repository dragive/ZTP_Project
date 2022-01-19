package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.dto.interfaces.ISala;

@Builder
@Data
public class SalaDTO implements ISala {
  private Long id;
  private String nazwa;
  private Long liczbaMiejsc;
  private Long liczbaRzedow;
  private Long liczbaMiejscWRzedzie;
  private Boolean czy3d;
  private String opis;

  @Override
  public String getDescription() {
    return "";
  }
}
