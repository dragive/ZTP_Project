package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.ui.Frames.LinuxFrame;
import org.example.ui.Frames.MacOSFrame;
import org.example.ui.Frames.MainFrame;
import org.example.ui.Frames.WindowsFrame;

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
