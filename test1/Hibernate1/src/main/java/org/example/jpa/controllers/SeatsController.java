package org.example.jpa.controllers;

import org.example.jpa.entities.FotelEntity;
import org.example.jpa.entities.RezerwacjaEntity;
import org.example.jpa.entities.SalaEntity;
import org.example.jpa.repositories.FotelRepository;
import org.example.jpa.repositories.SalaRepository;
import org.example.services.DatabaseService;
import org.example.ui.views.PopUps;
import org.example.ui.views.RoomViews.AddRoomView;
import org.example.ui.views.RoomViews.RoomListView;
import org.example.ui.views.RoomViews.RoomView;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SeatsController {
    private static SeatsController instance;

    FotelRepository fotelRepository;

    JFrame frame;

    private SeatsController(JFrame frame) {
        this.frame = frame;
        SessionFactory sessionFactory = DatabaseService.getInstance().getSessionFactory();
        fotelRepository = FotelRepository.builder().sessionFactory(sessionFactory).build();
    }

    public static SeatsController getInstance(JFrame frame) {
        if(instance==null) instance = new SeatsController(frame);
        return instance;
    }

    public void create(SalaEntity room) {
        List<FotelEntity> fotelEntities = RoomController.getInstance(frame).roomRepository.getById(room.getId()).getFotele();
            Long numer=1L;
            try {
                for(long i=0;i<room.getLiczbaRzedow();i++)
                {
                    for(long j=0;j<room.getLiczbaMiejscWRzedzie();j++) {
                        FotelEntity fotelEntity = new FotelEntity();
                        fotelEntity.setId(SeatsController.getInstance(frame).fotelRepository.getNewId());
                        fotelEntity.setNumer(numer++);
                        fotelEntity.setRzad(i+1L);
                        fotelEntity.setFotelReservations(null);
                        fotelEntity.setSalaEntity(RoomController.getInstance(frame).roomRepository.getById(room.getId()));
                        fotelEntities.add(fotelEntity);
                        SeatsController.getInstance(frame).fotelRepository.save(fotelEntity);
                    }
                }
            }
            catch (Exception ex){
                PopUps.Error("Wystąpił błąd podczas dodawania foteli!");
            }
        //}
        //long newRoomId = newRoomEntity.getId();
        //newRoomEntity = roomRepository.getById(newRoomId);
        //newRoomEntity.setFotele(fotelEntities);
        //roomRepository.update(newRoomEntity);
    }

}
