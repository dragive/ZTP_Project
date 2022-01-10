package org.example.ui.views.RoomViews;

import lombok.Getter;
import lombok.Setter;
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

    JButton seans = new JButton("Seanse");
    JButton delete = new JButton("Usuń");
    JButton exit = new JButton("Wstecz");

    public RoomView(SalaEntity room) {
        super(new GridLayout(0,2));

        czy3D.setEnabled(false);
        czy3D.setSelected(room.getCzy3d());

        roomName = new JLabel(room.getNazwa());
        rows = new JLabel(room.getLiczbaRzedow().toString());
        cols = new JLabel(room.getLiczbaMiejscWRzedzie().toString());
        seats = new JLabel(room.getLiczbaMiejsc().toString());

        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);

        this.add(roomNameText,BaseViewConstraint.CENTER);
        this.add(roomName,BaseViewConstraint.CENTER);

        this.add(rowsText,BaseViewConstraint.CENTER);
        this.add(rows,BaseViewConstraint.CENTER);

        this.add(colsText,BaseViewConstraint.CENTER);
        this.add(cols,BaseViewConstraint.CENTER);

        this.add(seatsText,BaseViewConstraint.CENTER);
        this.add(seats,BaseViewConstraint.CENTER);

        this.add(czy3DText,BaseViewConstraint.CENTER);
        this.add(czy3D,BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,3),BaseViewConstraint.FOOTER);

        this.add(seans,BaseViewConstraint.FOOTER);
        this.add(delete,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
