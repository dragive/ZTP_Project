package org.example.jpa.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "SALA")
public class SalaEntity {
  @Id
  @Column(name = "SALA_ID", nullable = false)
  private Long id;

  @Column(name = "LICZBA_MIEJSC", nullable = false)
  private Long liczbaMiejsc;

  @Column(name = "LICZBA_RZEDOW", nullable = false)
  private Long liczbaRzedow;

  @Column(name = "MIEJSC_W_RZEDZIE", nullable = false)
  private Long miejscWRzedzie;

  @Column(name = "CZY_3D", nullable = false)
  private Boolean czy3d = false;

  @Column(name = "NAZWA", length = 100)
  private String nazwa;

  @Column(name = "CZY_AUDIO")
  private Boolean czyAudio;

  @Column(name = "CZY_LEPSZE_SIEDZENIA")
  private Boolean czyLepszeSiedzenia;

  @Column(name = "CZY_NIEPELNOSPRAWNI")
  private Boolean czyNiepelnosprawni;

  @ManyToOne(optional = false)
  @JoinColumn(name = "KINO_KINO_ID", nullable = false)
  private KinoEntity kino;

  @OneToMany(fetch= FetchType.EAGER)
  @JoinColumn(name="Sala_id")
  private List<SeansEntity> seanse;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name="Sala_id",updatable = false)
  private List<FotelEntity> fotele;

}