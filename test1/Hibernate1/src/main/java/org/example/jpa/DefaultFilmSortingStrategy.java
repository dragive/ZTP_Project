package org.example.jpa;

import org.example.jpa.controllers.FilmController;
import org.example.ui.views.FilmViews.FilmListView;

public class DefaultFilmSortingStrategy extends FilmSortingStrategy {

  public DefaultFilmSortingStrategy(FilmController context){
    super(context);
  }

  @Override
  public void sort(){

  }


}
