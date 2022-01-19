
package org.example.dto.decorator;

        import lombok.Getter;
        import lombok.Setter;
        import org.example.dto.interfaces.ISala;

@Getter
@Setter
public class Room2dDecorator implements ISala {
  private ISala iSala;
  public Room2dDecorator(ISala iSala){
    this.iSala = iSala;
  }

  @Override
  public String getDescription() {
    return "Room2dDecorator " + iSala.getDescription();
  }
}