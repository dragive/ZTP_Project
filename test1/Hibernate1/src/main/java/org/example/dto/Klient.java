package org.example.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper=true)
public class Klient extends Osoba {
    private String klientField;
}
