package org.example.jpa.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;
import static org.hibernate.annotations.CascadeType.REMOVE;

@Table(name = "SALA")
@Entity
@Getter
@Setter
@ToString
public class SalaEntity {
    @Id
    @Column(name = "SALA_ID", nullable = false)
    private Long id;

    @Column(name = "NAZWA", nullable = true, length = 100)
    private String nazwa;

    @Column(name = "LICZBA_MIEJSC", nullable = false)
    private Long liczbaMiejsc;

    @Column(name = "LICZBA_RZEDOW", nullable = false)
    private Long liczbaRzedow;

    @Column(name = "MIEJSC_W_RZEDZIE", nullable = false)
    private Long liczbaMiejscWRzedzie;

    @Column(name = "CZY_3D", nullable = false)
    private Boolean czy3d = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "KINO_KINO_ID", nullable = false)
    private KinoEntity kino;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="Sala_id")
    private List<SeansEntity> seanse;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="Sala_id",updatable = false)
    private List<FotelEntity> fotele;

}