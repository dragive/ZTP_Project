package org.example.ui.views.FilmViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.entities.FilmEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class FilmView extends BaseView {
    JLabel titleText = new JLabel("Tytuł");
    JLabel title;

    JLabel opisText = new JLabel("Opis");
    JLabel opis;

    JLabel dataWydaniaText = new JLabel("Data wydania");
    JLabel dataWydania;

    JLabel wiekText = new JLabel("Wiek");
    JLabel wiek;

    JLabel czasTrwaniaText = new JLabel("Czas trwania");
    JLabel czasTrwania;

    JLabel czy3DText = new JLabel("Czy w 3D");
    JCheckBox czy3D = new JCheckBox();

    JLabel czyNapisyText = new JLabel("Czy z napisami");
    JCheckBox czyNapisy = new JCheckBox();

    JLabel czyDubbingText = new JLabel("Czy z dubbingiem");
    JCheckBox czyDubbing = new JCheckBox();

    JLabel czyOriginalText = new JLabel("Czy oryginalny");
    JCheckBox czyOriginal = new JCheckBox();

    JButton edit = new JButton("Edytuj");
    JButton delete = new JButton("Usuń");
    JButton exit = new JButton("Wstecz");

    public FilmView(FilmEntity filmEntity) {
        super(new GridLayout(0,2));

        title = new JLabel(filmEntity.getTitle());
        opis = new JLabel(filmEntity.getOpis());
        dataWydania = new JLabel(filmEntity.getDataWydania().toString());
        wiek = new JLabel(filmEntity.getWiek().toString());
        czasTrwania = new JLabel(filmEntity.getCzasTrwania().toString());
        czy3D.setSelected(filmEntity.getCzy3d());
        czy3D.setEnabled(false);
        czyNapisy.setSelected(filmEntity.getCzyNapisy());
        czyNapisy.setEnabled(false);
        czyDubbing.setSelected(filmEntity.getCzyDubbing());
        czyDubbing.setEnabled(false);
        czyOriginal.setSelected(filmEntity.getCzyOriginal());
        czyOriginal.setEnabled(false);

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(titleText, BaseViewConstraint.CENTER);
        this.add(title, BaseViewConstraint.CENTER);

        this.add(opisText, BaseViewConstraint.CENTER);
        this.add(opis, BaseViewConstraint.CENTER);

        this.add(dataWydaniaText, BaseViewConstraint.CENTER);
        this.add(dataWydania, BaseViewConstraint.CENTER);

        this.add(wiekText, BaseViewConstraint.CENTER);
        this.add(wiek, BaseViewConstraint.CENTER);

        this.add(czasTrwaniaText, BaseViewConstraint.CENTER);
        this.add(czasTrwania, BaseViewConstraint.CENTER);

        this.add(czy3DText, BaseViewConstraint.CENTER);
        this.add(czy3D, BaseViewConstraint.CENTER);

        this.add(czyNapisyText, BaseViewConstraint.CENTER);
        this.add(czyNapisy, BaseViewConstraint.CENTER);

        this.add(czyDubbingText, BaseViewConstraint.CENTER);
        this.add(czyDubbing, BaseViewConstraint.CENTER);

        this.add(czyOriginalText, BaseViewConstraint.CENTER);
        this.add(czyOriginal, BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);

        this.add(edit,BaseViewConstraint.FOOTER);
        this.add(delete,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
