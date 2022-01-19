package org.example.dto.mappers;

import org.example.dto.SalaDTO;
import org.example.jpa.entities.SalaEntity;

public class SalaMapper {
  public static SalaDTO mapToDTO(SalaEntity entity) {
    return SalaDTO.builder()
            .id(entity.getId())
            .czy3d(entity.getCzy3d())
            .liczbaMiejsc(entity.getLiczbaMiejsc())
            .liczbaMiejscWRzedzie(entity.getLiczbaMiejscWRzedzie())
            .liczbaRzedow(entity.getLiczbaRzedow())
            .nazwa(entity.getNazwa())
            .build();
  }
}
