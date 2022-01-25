package org.example.ui.views.RoomViews;

import lombok.Getter;
import lombok.Setter;
import org.example.dto.SalaDTO;
import org.example.jpa.entities.MagazynEntity;
import org.example.jpa.entities.SalaEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class RoomView extends BaseView {
    JLabel roomNameText = new JLabel("Nazwa pokoju");
    JLabel roomName;

    JLabel colsText = new JLabel("Liczba miejsc w rzędzie");
    JLabel cols;

    JLabel rowsText = new JLabel("Liczba rzędów");
    JLabel rows;

    JLabel seatsText = new JLabel("Liczba Miejsc");
    JLabel seats;

    JLabel czy3DText = new JLabel("Sala obsługuje technologię 3D");
    JCheckBox czy3D = new JCheckBox();

    JLabel czyLepszeSiedzeniaText = new JLabel("Sala posiada lepsze siedzenia");
    JCheckBox czyLepszeSiedzenia = new JCheckBox();

    JLabel czyLepszyDzwiekText = new JLabel("Sala posiada lepsze nagłośnienie");
    JCheckBox czyLepszyDzwiek = new JCheckBox();

    JLabel czySiedzeniaDlaNiepelosprawnychText = new JLabel("Sala posiada siedzenia na parterze");
    JCheckBox czySiedzeniaDlaNiepelosprawnych = new JCheckBox();

    JLabel opisText = new JLabel("Opis słowny komponentów sali");
    JLabel opis;


    JButton seans = new JButton("Seanse");
    JButton delete = new JButton("Usuń");
    JButton exit = new JButton("Wstecz");

    public RoomView(SalaDTO room) {
        super(new GridLayout(0,2));

        czy3D.setEnabled(false);
        czy3D.setSelected(room.getCzy3d());

        czyLepszeSiedzenia.setEnabled(false);
        czyLepszeSiedzenia.setSelected(room.getCzyLepszeSiedzenia());

        czyLepszyDzwiek.setEnabled(false);
        czyLepszyDzwiek.setSelected(room.getCzyLepszyDzwiek());

        czySiedzeniaDlaNiepelosprawnych.setEnabled(false);
        czySiedzeniaDlaNiepelosprawnych.setSelected(room.getCzyMiejscaDlaNiepelnosprawnych());

        roomName = new JLabel(room.getNazwa());
        rows = new JLabel(room.getLiczbaRzedow().toString());
        cols = new JLabel(room.getLiczbaMiejscWRzedzie().toString());
        seats = new JLabel(room.getLiczbaMiejsc().toString());
        opis = new JLabel("<html>"+room.getDescription()+"</html>");

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.NORTH);

        this.add(roomNameText,BaseViewConstraint.NORTH);
        this.add(roomName,BaseViewConstraint.NORTH);

        this.add(rowsText,BaseViewConstraint.NORTH);
        this.add(rows,BaseViewConstraint.NORTH);

        this.add(colsText,BaseViewConstraint.NORTH);
        this.add(cols,BaseViewConstraint.NORTH);

        this.add(seatsText,BaseViewConstraint.NORTH);
        this.add(seats,BaseViewConstraint.NORTH);

        this.add(czy3DText,BaseViewConstraint.NORTH);
        this.add(czy3D,BaseViewConstraint.NORTH);

        this.add(czyLepszeSiedzeniaText,BaseViewConstraint.NORTH);
        this.add(czyLepszeSiedzenia,BaseViewConstraint.NORTH);

        this.add(czyLepszyDzwiekText,BaseViewConstraint.NORTH);
        this.add(czyLepszyDzwiek,BaseViewConstraint.NORTH);

        this.add(czySiedzeniaDlaNiepelosprawnychText,BaseViewConstraint.NORTH);
        this.add(czySiedzeniaDlaNiepelosprawnych,BaseViewConstraint.NORTH);

        this.add(opisText,BaseViewConstraint.NORTH);
        this.add(opis,BaseViewConstraint.NORTH);

        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);

        this.add(seans,BaseViewConstraint.FOOTER);
        this.add(delete,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
