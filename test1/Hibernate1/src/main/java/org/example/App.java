package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.dto.SalaDTO;
import org.example.dto.decorator.Room2dDecorator;
import org.example.dto.decorator.Room3dDecorator;
import org.example.dto.decorator.RoomSurroundingAudioDecorator;
import org.example.dto.interfaces.ISala;
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
        SalaDTO salaDTO = SalaDTO.builder()
                .nazwa("nazwa")
                .liczbaRzedow(123l)
                .id(1l)
                .liczbaMiejsc(123l)
                .liczbaMiejscWRzedzie(1l)
                .czy3d(true)
//fixme potrzeba dodania do bazy itp            .czy2d()
                .opis("")
                .build();
        
// fixme do testow zakomentowane

//        ISala iSala = new Room2dDecorator(new Room3dDecorator(new RoomSurroundingAudioDecorator(salaDTO)));
//        System.out.println(iSala.getDescription());
//
//        BasicConfigurator.configure();
//        Logger.getRootLogger().setLevel(Level.INFO);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainFrame();
//            }
//        });

    }
}
