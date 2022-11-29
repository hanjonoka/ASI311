package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
import com.ensta.myfilmlist.model.Film;

import java.util.List;

public interface FilmDAO {
    public List<Film> findAll();
}
