package org.example.dto.decorator;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.interfaces.ISala;

@Getter
@Setter
public class RoomSurroundingAudioDecorator implements ISala {
  private ISala iSala;
  public RoomSurroundingAudioDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "RoomSurroundingAudioDecorator " + iSala.getDescription();
  }
}
