package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
    private final static String FIND_ALL_FILMS_WITH_REAL_QUERY =
            "SELECT * FROM Film LEFT JOIN Realisateur ON Film.realisateur_id=Realisateur.id";

    @Override
    public List<Film> findAll() {
        List<Film> liste = new ArrayList<>();
        liste = jdbcTemplate.query(FIND_ALL_FILMS_WITH_REAL_QUERY, (rs, rownum) -> {
            Film f = new Film();
            f.setId(rs.getInt("Film.id"));
            f.setDuree(rs.getInt("Film.duree"));
            f.setTitre(rs.getString("Film.titre"));
            Realisateur r = new Realisateur(
                    rs.getInt("Realisateur.id"),
                    rs.getString("Realisateur.nom"),
                    rs.getString("Realisateur.prenom"),
                    rs.getDate("Realisateur.date_naissance").toLocalDate(),
                    rs.getBoolean("Realisateur.celebre")
            );
            f.setRealisateur(r);
            return f;
        });
        return liste;
    }

    @Override
    public Film save(Film f) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator creator = conn -> {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Film(titre, duree, realisateur_id) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,f.getTitre());
            statement.setInt(2,f.getDuree());
            statement.setLong(3, f.getRealisateur().getId());
            return statement;
        };
        jdbcTemplate.update(creator, keyHolder);
        f.setId(keyHolder.getKey().longValue());
        return f;
    }
}