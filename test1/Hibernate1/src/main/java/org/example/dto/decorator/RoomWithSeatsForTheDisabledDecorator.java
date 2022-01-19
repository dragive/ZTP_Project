package org.example.dto.decorator;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
public class RoomWithSeatsForTheDisabledDecorator implements ISala {
  private ISala iSala;
  public RoomWithSeatsForTheDisabledDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "RoomWithSeatsForTheDisabledDecorator " + iSala.getDescription();
  }
}
