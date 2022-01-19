package org.example.dto.decorator;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
public class RoomWithBetterSeatsDecorator implements ISala {
  private ISala iSala;
  public RoomWithBetterSeatsDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "RoomWithBetterSeatsDecorator " + iSala.getDescription();
  }
}
