package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.ui.Frames.LinuxFrame;
import org.example.ui.Frames.MacOSFrame;
import org.example.ui.Frames.MainFrame;
import org.example.ui.Frames.WindowsFrame;
import org.example.dto.SalaDTO;
import org.example.dto.decorator.Room2dDecorator;
import org.example.dto.decorator.Room3dDecorator;
import org.example.dto.decorator.RoomSurroundingAudioDecorator;
import org.example.dto.decorator.RoomWithBetterSeatsDecorator;
import org.example.dto.decorator.RoomWithSeatsForTheDisabledDecorator;
import org.example.dto.interfaces.ISala;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static MainFrame frame;

    public static void main( String[] args )
    {
        /*SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();

        KinoRepository kinoRepository = KinoRepository.builder().sessionFactory(sessionFactory).build();

        System.out.println(kinoRepository.getAll());


        List<KinoEntity> kinoEntityList = kinoRepository.getAll();

        for(KinoEntity kinoEntity:kinoEntityList) {
            System.out.println(kinoEntity);
        }*/
        Boolean debug = false;
        if(debug){
        SalaDTO salaDTO = new SalaDTO(new RoomSurroundingAudioDecorator(new Room2dDecorator(new Room3dDecorator(new RoomWithBetterSeatsDecorator(new RoomWithSeatsForTheDisabledDecorator())))));

        ISala iSala = salaDTO;
        System.out.println(iSala.getDescription());





        }
        else {

        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chooseOS();
                System.out.println(OS);
            }
        });
        }
    }

    private static void chooseOS() {
        if (isWindows()) {
            frame = new WindowsFrame();
        }
        else if (isMac()) {
            frame = new MacOSFrame();
        }
        else if (isUnix()) {
            frame = new LinuxFrame();
        }
        else {
            frame = new WindowsFrame();
        }
    }

    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix")
                || OS.contains("nux")
                || OS.indexOf("aix") > 0);
    }
}
