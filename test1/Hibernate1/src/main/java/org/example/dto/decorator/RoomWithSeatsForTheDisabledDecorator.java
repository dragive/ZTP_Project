package org.example.dto.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.SalaDTO;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
@NoArgsConstructor
public class RoomWithSeatsForTheDisabledDecorator implements ISala {
  private ISala iSala;
  public RoomWithSeatsForTheDisabledDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {

    return "specjalne miejsca dla osób niepełnosprawnych" + (iSala != null ?  ", "+iSala.getDescription() : ".");
//    return ""+((true ?iSala.getDescription():""))+"specjalne miejsca dla osób niepełnosprawnych "+(iSala instanceof SalaDTO?"":" ") ;
  }
}
