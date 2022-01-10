package org.example.ui.views.MagazynViews;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.jpa.entities.KinoEntity;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class MagazynItemsView extends BaseView {

    JButton exit = new JButton("Wstecz");

    public MagazynItemsView(List<JButton> buttonList) {
        super(new GridLayout(0,1));

        mountPanels();

        for(JButton button: buttonList) {
            this.add(button, BaseViewConstraint.NORTH);
        }

        this.add(exit,BaseViewConstraint.FOOTER);
    }
}
