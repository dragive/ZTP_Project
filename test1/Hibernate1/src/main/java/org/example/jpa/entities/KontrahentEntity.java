package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "KONTRAHENT")
@Entity
@Getter
@Setter
@ToString
public class KontrahentEntity {
    @Id
    @Column(name = "KONTRAHENT_ID", nullable = false)
    private Long id;

    @Column(name = "NIP", length = 13)
    private String nip;

    @Column(name = "NAZWA_FIRMY", nullable = false, length = 100)
    private String nazwaFirmy;

    @Column(name = "MIASTO", nullable = false, length = 100)
    private String miasto;

    @Column(name = "ADRES", nullable = false, length = 100)
    private String adres;

}