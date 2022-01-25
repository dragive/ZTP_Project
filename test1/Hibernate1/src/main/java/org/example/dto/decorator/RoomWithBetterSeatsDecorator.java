package org.example.dto.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.SalaDTO;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
@NoArgsConstructor
public class RoomWithBetterSeatsDecorator implements ISala {
  private ISala iSala;
  public RoomWithBetterSeatsDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {

    return "ulepszone siedzenia z pakietem COMFORT" + (iSala != null ? ", "+ iSala.getDescription() : ".");
//    return ""+((true ?iSala.getDescription():""))+"ulepszone siedzenia z pakietem COMFORT "+(iSala instanceof SalaDTO?"":" ") ;
  }
}
