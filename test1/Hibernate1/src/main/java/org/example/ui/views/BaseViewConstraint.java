package org.example.ui.views;

import javax.persistence.criteria.CriteriaBuilder;

public enum BaseViewConstraint {

    /**
     * część widoku renderowana od góry od lewej strony
     */
    HEADER(-1),


    /**
     * Widok generowany od góry ale zajmujący całą pozostałą przestrzeń między headerem a Footerem.
     * Ważną częścią jest to, że obiekty nie zajmują większości
     */
    NORTH(0),


    /**
     * Generowanie w centralnym bloku. Obiekty znajdujące się w nim nie zajmują
     */
    CENTER(1),


    /**
     * Dodawanie przycisków w bliżej nie ustalony sposób w footerze TODO
     */
    FOOTER(2);
    private final Integer identyfier;


     BaseViewConstraint(Integer identyfier){
        this.identyfier = identyfier;
    }
}
