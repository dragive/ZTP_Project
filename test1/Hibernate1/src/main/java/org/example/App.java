package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.ui.MainFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();

        KinoRepository kinoRepository = KinoRepository.builder().sessionFactory(sessionFactory).build();

        System.out.println(kinoRepository.getAll());


        List<KinoEntity> kinoEntityList = kinoRepository.getAll();

        for(KinoEntity kinoEntity:kinoEntityList) {
            System.out.println(kinoEntity);
        }*/
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });

    }
}
