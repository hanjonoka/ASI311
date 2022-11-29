package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcFilmDAO implements FilmDAO {
    private JdbcTemplate jdbcTemplate = ConnectionManager.getJdbcTemplate();

    private JdbcFilmDAO() {}

    private static JdbcFilmDAO Instance=null;
    public static JdbcFilmDAO getInstance() {
        if(Instance==null) {
            Instance = new JdbcFilmDAO();
        }
        return Instance;
    }
    private final static String FIND_ALL_FILMS_QUERY = "SELECT * FROM Film";

    @Override
    public List<Film> findAll() {
        List<Film> liste = new ArrayList<>();
        liste = jdbcTemplate.query(FIND_ALL_FILMS_QUERY, (rs, rownum) -> {
            Film f = new Film();
            f.setId(rs.getInt("id"));
            f.setDuree(rs.getInt("duree"));
            f.setTitre(rs.getString("titre"));
            return f;
        });
        return liste;
    }
}
