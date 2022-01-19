package org.example.dto.decorator;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
public class Room3dDecorator implements ISala {
  private ISala iSala;
  public Room3dDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "Room3dDecorator " + iSala.getDescription();
  }
}
