package org.example.dto.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.SalaDTO;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
@NoArgsConstructor
public class Room3dDecorator extends RoomDecorator {
  private ISala iSala;
  public Room3dDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "opcję oglądania filmów 3D" + (iSala != null ?  ", "+iSala.getDescription() : ".");

//    return ""+iSala.getDescription()+"opcje oglądania filmów 3D" +(iSala instanceof SalaDTO?"":" ");
  }
}
