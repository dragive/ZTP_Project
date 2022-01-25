package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.dto.interfaces.ISala;

//@Builder
@Data
public class SalaDTO implements ISala {
  private Long id;
  private String nazwa;
  private Long liczbaMiejsc;
  private Long liczbaRzedow;
  private Long liczbaMiejscWRzedzie;
  private Boolean czy3d;
  private Boolean czyLepszeSiedzenia;
  private Boolean czyLepszyDzwiek;
  private Boolean czyMiejscaDlaNiepelnosprawnych;
  private String opis;

  private ISala iSala;

  public SalaDTO() {
  }

  public SalaDTO(ISala iSala) {
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {

    if (this.iSala == null) {
      return "Sala posiada podstawową konfigurację";
    }
    else{
      return "Sala posiada " + iSala.getDescription();
    }
  }
}
