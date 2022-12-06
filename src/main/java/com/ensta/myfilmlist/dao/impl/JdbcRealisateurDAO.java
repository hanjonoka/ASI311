package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.persistence.ConnectionManager;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcRealisateurDAO implements RealisateurDAO {

    private JdbcTemplate jdbcTemplate = ConnectionManager.getJdbcTemplate();

    private JdbcRealisateurDAO() {}

    private static JdbcRealisateurDAO Instance = null;
    public static JdbcRealisateurDAO getInstance() {
        if(Instance==null) {
            Instance=new JdbcRealisateurDAO();
        }
        return Instance;
    }


    @Override
    public List<Realisateur> findAll() {
        return jdbcTemplate.query("SELECT * FROM Realisateur", (rs, numrow) -> {
           Realisateur realisateur = new Realisateur();
           realisateur.setId(rs.getInt("id"));
           realisateur.setNom(rs.getString("nom"));
           realisateur.setPrenom(rs.getString("prenom"));
           realisateur.setCelebre(rs.getBoolean("celebre"));
           realisateur.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
           return realisateur;
        });
    }

    @Override
    public Realisateur findByNomAndPrenom(String nom, String prenom) {
        try {
        Realisateur real = jdbcTemplate.queryForObject(
                "SELECT * FROM Realisateur WHERE nom=? AND prenom=?",
                (rs, rownum) -> {
                return new Realisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("date_naissance").toLocalDate(),
                        rs.getBoolean("celebre"));
                },
                nom, prenom);
        return real;
        }catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Realisateur> findById(long id) {
        try {
            Realisateur real = jdbcTemplate.queryForObject(
                    "SELECT * FROM Realisateur WHERE id=?",
                    (rs, rownum) -> {
                        return new Realisateur(
                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getDate("date_naissance").toLocalDate(),
                                rs.getBoolean("celebre")
                        );
                    }
                    , id
            );
            return Optional.of(real);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

