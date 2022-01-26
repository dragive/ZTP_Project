package org.example.ui.views.RoomViews;

import lombok.Getter;
import lombok.Setter;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddRoomView extends BaseView {
    JLabel roomNameText = new JLabel("Nazwa pokoju");
    JTextField roomName = new JTextField();

    JLabel colsText = new JLabel("Liczba miejsc w rzędzie");
    JTextField cols = new JTextField();

    JLabel rowsText = new JLabel("Liczba rzędów");
    JTextField rows = new JTextField();

    JLabel czy3DText = new JLabel("Sala obsługuje technologię 3D");
    JCheckBox czy3D = new JCheckBox();

    JLabel czyLepszyDzwiekText = new JLabel("Sala posiada lepsze nagłośnienie");
    JCheckBox czyLepszyDzwiek = new JCheckBox();

    JLabel czyLepszeMiejscaText = new JLabel("Sala posiada miejca wyższego standardu");
    JCheckBox czyLepszeMiejsca = new JCheckBox();

    JLabel czyDlaNiepelnosprawnychText = new JLabel("Sala została przystosowana dla osób niepełnosprawnych");
    JCheckBox czyDlaNiepelnosprawnych = new JCheckBox();

    JButton accept = new JButton("Zaakceptuj");
    JButton exit = new JButton("Wstecz");

    public AddRoomView() {
        super(new GridLayout(0,2));
        mountPanels();
        setLayout(new GridLayout(0,2), BaseViewConstraint.CENTER);
        this.add(roomNameText,BaseViewConstraint.CENTER);
        this.add(roomName,BaseViewConstraint.CENTER);

        this.add(rowsText,BaseViewConstraint.CENTER);
        this.add(rows,BaseViewConstraint.CENTER);

        this.add(colsText,BaseViewConstraint.CENTER);
        this.add(cols,BaseViewConstraint.CENTER);

        this.add(czy3DText,BaseViewConstraint.CENTER);
        this.add(czy3D,BaseViewConstraint.CENTER);

        this.add(czyLepszeMiejscaText,BaseViewConstraint.CENTER);
        this.add(czyLepszeMiejsca,BaseViewConstraint.CENTER);

        this.add(czyLepszyDzwiekText,BaseViewConstraint.CENTER);
        this.add(czyLepszyDzwiek,BaseViewConstraint.CENTER);

        this.add(czyDlaNiepelnosprawnychText,BaseViewConstraint.CENTER);
        this.add(czyDlaNiepelnosprawnych,BaseViewConstraint.CENTER);

        setLayout(new GridLayout(0,2),BaseViewConstraint.FOOTER);

        this.add(accept,BaseViewConstraint.FOOTER);
        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
