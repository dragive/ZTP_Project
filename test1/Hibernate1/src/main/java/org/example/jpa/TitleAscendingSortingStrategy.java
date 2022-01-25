package org.example.jpa;

import org.example.jpa.controllers.FilmController;
import org.example.jpa.entities.FilmEntity;
import org.example.ui.views.FilmViews.FilmListView;

import java.util.Comparator;
import java.util.stream.Collectors;

public class TitleAscendingSortingStrategy extends FilmSortingStrategy {

  public TitleAscendingSortingStrategy(FilmController context){
    super(context);
  }

  @Override
  public void sort(){
    getContext().getFilmListView().setFilmEntityList(getContext().getFilmListView().getFilmEntityList().stream().sorted(Comparator.comparing(FilmEntity::getTitle)).collect(Collectors.toList()));
  }


}
