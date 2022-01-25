package org.example.dto.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.SalaDTO;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
@NoArgsConstructor
public class RoomSurroundingAudioDecorator implements ISala {
  private ISala iSala;
  public RoomSurroundingAudioDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "opracowane na specjalne zamówienie nagłośnienie" + (iSala != null ? ", "+iSala.getDescription() : ".");
//    return ""+((true ?iSala.getDescription():""))+"opracowane na specjalne zamówienie nagłośnienie "+(iSala instanceof SalaDTO?"":" ");
  }
}
