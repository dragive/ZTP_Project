package org.example.jpa;

import org.example.jpa.entities.FilmEntity;
import org.example.ui.views.FilmViews.FilmListView;
import java.util.List;

public class DefaultFilmSortingStrategy extends FilmSortingStrategy {

  public DefaultFilmSortingStrategy(FilmListView context){
    super(context);
  }

  @Override
  public List<FilmEntity> sort(List<FilmEntity > list){
    return list;
  }


}
