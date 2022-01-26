
package org.example.dto.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.SalaDTO;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
@NoArgsConstructor
public class Room2dDecorator extends RoomDecorator {
  private ISala iSala;

  public Room2dDecorator(ISala iSala) {
    super();
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {

    return "opcję oglądania filmów 2D" + (iSala != null ?  ", "+iSala.getDescription() : ".");
  }
}