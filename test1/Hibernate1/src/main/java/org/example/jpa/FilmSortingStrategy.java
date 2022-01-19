package org.example.jpa;

import org.example.jpa.entities.FilmEntity;
import org.example.ui.views.FilmViews.FilmListView;

import java.util.List;

public abstract class FilmSortingStrategy {
  private FilmListView context;
  public FilmSortingStrategy(FilmListView filmListView) {
    this.context = filmListView;
  }

  public abstract List<FilmEntity> sort(List<FilmEntity> list);
}
