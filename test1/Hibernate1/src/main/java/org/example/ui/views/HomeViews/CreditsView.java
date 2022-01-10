package org.example.ui.views.HomeViews;

import org.example.ui.views.BaseView;
import org.example.ui.views.BaseViewConstraint;

import javax.swing.*;
import java.awt.*;

public class CreditsView extends BaseView {
    public CreditsView() {
        super(new GridLayout(0,1));

        mountPanels();

        this.add(new JLabel("Autorzy Projektu:"), BaseViewConstraint.NORTH);
        this.add(new JLabel("Maciek Fender"),BaseViewConstraint.NORTH);
        this.add(new JLabel("Kacper Chrost"),BaseViewConstraint.NORTH);
        this.add(new JLabel("Krzysztof Funkowski"),BaseViewConstraint.NORTH);
    }
}
