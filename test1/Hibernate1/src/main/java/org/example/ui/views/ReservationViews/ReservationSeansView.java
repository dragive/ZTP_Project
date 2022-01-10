package org.example.ui.views.ReservationViews;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.ReservationController;
import org.example.jpa.entities.*;
import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;
import org.example.ui.views.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReservationSeansView extends BaseView {

    JButton exit = new JButton("Wstecz");

    JButton accept = new JButton("Zarezerwuj");

    Object previous;

    public ReservationSeansView(List<JButton> fotels,int cols) {
        super(new BorderLayout());
//        mountPanels();

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(0,cols)/*,BaseViewConstraint.FOOTER*/);

        for(JButton button:fotels){
            wrapper.add(button/*, BaseViewConstraint.NORTH*/);
        }

        this.add(wrapper,BorderLayout.CENTER);



        JPanel footer = new JPanel();
        footer.setLayout(new GridLayout(1,2));
        footer.add(accept);
        footer.add(exit);
        this.add(footer, BorderLayout.SOUTH);
    }

}
