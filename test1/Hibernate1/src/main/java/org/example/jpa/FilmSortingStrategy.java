package org.example.jpa;

import lombok.Getter;
import lombok.Setter;
import org.example.jpa.controllers.FilmController;
import org.example.ui.views.FilmViews.FilmListView;


@Getter
@Setter
public abstract class FilmSortingStrategy {
  private FilmController context;
  public FilmSortingStrategy(FilmController filmListView) {
    this.context = filmListView;
  }

//  public abstract List<FilmEntity> sort(List<FilmEntity> list);
//
  public abstract void sort();
}
