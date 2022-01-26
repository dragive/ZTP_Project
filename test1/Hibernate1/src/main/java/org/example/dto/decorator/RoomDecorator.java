package org.example.dto.decorator;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
public abstract class RoomDecorator implements ISala {
  public ISala iSala;

  public RoomDecorator(ISala iSala){
    this.iSala = iSala;
  }

  public RoomDecorator() {

  }

  @Override
  public String getDescription(){
    return "Room Decorator " + iSala.getDescription();
  }


}
