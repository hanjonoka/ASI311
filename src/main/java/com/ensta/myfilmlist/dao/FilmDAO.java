package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
import com.ensta.myfilmlist.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmDAO {
    public List<Film> findAll();
    public Film save(Film f);
    Optional<Film> findById(long id);
    void delete(Film film);
    List<Film> findByRealisateurId(long realisateurId);

}
