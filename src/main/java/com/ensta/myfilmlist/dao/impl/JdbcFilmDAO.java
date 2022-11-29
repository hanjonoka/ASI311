package com.ensta.myfilmlist.dao.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.persistence.ConnectionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcFilmDAO implements FilmDAO {
    private DataSource datasource = ConnectionManager.getDataSource();

    private final static String FIND_ALL_FILMS_QUERY = "SELECT * FROM Film";

    @Override
    public List<Film> findAll() {
        List<Film> liste = new ArrayList<>();
        try(Connection connection = datasource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FILMS_QUERY);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()) {
                    Film f = new Film();
                    f.setId(resultSet.getInt("id"));
                    f.setDuree(resultSet.getInt("duree"));
                    f.setTitre(resultSet.getString("titre"));
                    liste.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return liste;
    }
}
