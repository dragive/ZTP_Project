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
public class AddFilmView extends BaseView {

    JLabel titleText = new JLabel("Tytuł");
    JTextField title = new JTextField();

    JLabel opisText = new JLabel("Opis");
    JTextField opis = new JTextField();

    JLabel dataWydaniaText = new JLabel("Data wydania (DD-MM-RRRR)");
    JTextField dataWydania = new JTextField();

    JLabel wiekText = new JLabel("Wiek");
    JTextField wiek = new JTextField();

    JLabel czasTrwaniaText = new JLabel("Czas trwania");
    JTextField czasTrwania = new JTextField();

    JLabel czy3DText = new JLabel("Czy w 3D");
    JCheckBox czy3D = new JCheckBox();

    JLabel czyNapisyText = new JLabel("Czy z napisami");
    JCheckBox czyNapisy = new JCheckBox();

    JLabel czyDubbingText = new JLabel("Czy z dubbingiem");
    JCheckBox czyDubbing = new JCheckBox();

    JLabel czyOriginalText = new JLabel("Czy oryginalny");
    JCheckBox czyOriginal = new JCheckBox();

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    public AddFilmView() {
        super(new GridLayout(0,2));

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

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
