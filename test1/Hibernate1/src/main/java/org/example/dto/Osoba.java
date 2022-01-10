package org.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public abstract class Osoba {
    private Integer id;
    public String name;
}
