package org.example.dto.mappers;

import org.example.dto.SalaDTO;
import org.example.dto.decorator.Room2dDecorator;
import org.example.dto.decorator.Room3dDecorator;
import org.example.dto.decorator.RoomSurroundingAudioDecorator;
import org.example.dto.decorator.RoomWithBetterSeatsDecorator;
import org.example.dto.decorator.RoomWithSeatsForTheDisabledDecorator;
import org.example.dto.interfaces.ISala;
import org.example.jpa.entities.SalaEntity;

public class SalaMapper {
  public static SalaDTO mapToDTO(SalaEntity entity) {

    ISala wrap = new Room2dDecorator();

    if (entity.getCzy3d()) {
      wrap = new Room3dDecorator(wrap);
    }
    if (entity.getCzyLepszeSiedzenia()) {
      wrap = new RoomWithBetterSeatsDecorator(wrap);
    }
    if (entity.getCzyNiepelnosprawni()) {
      wrap = new RoomWithSeatsForTheDisabledDecorator(wrap);
    }
    if (entity.getCzyAudio()) {
      wrap = new RoomSurroundingAudioDecorator(wrap);
    }

    SalaDTO salaDTO = new SalaDTO(wrap);


    salaDTO.setId(entity.getId());
    salaDTO.setCzy3d(entity.getCzy3d());
    salaDTO.setCzyLepszeSiedzenia(entity.getCzyLepszeSiedzenia());
    salaDTO.setCzyMiejscaDlaNiepelnosprawnych(entity.getCzyNiepelnosprawni());
    salaDTO.setCzyLepszyDzwiek(entity.getCzyAudio());
    salaDTO.setLiczbaMiejsc(entity.getLiczbaMiejsc());
    salaDTO.setLiczbaMiejscWRzedzie(entity.getMiejscWRzedzie());
    salaDTO.setNazwa(entity.getNazwa());
    salaDTO.setLiczbaRzedow(entity.getLiczbaRzedow());

    return salaDTO;
  }
}
