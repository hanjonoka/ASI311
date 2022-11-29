package com.ensta.myfilmlist.service.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.service.MyFilmsService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.round;

public class MyFilmsServiceImpl implements MyFilmsService {
    public static final int NB_FILMS_MIN_REALISATEUR_CELEBRE = 3;

    FilmDAO filmDAO = JdbcFilmDAO.getInstance();

    @Override
    public Realisateur updateRealisateurCelebre(@NotNull Realisateur realisateur) throws ServiceException {
        if(realisateur.getFilmRealises().size()>= NB_FILMS_MIN_REALISATEUR_CELEBRE) {
            realisateur.setCelebre(true);
        }else{
            realisateur.setCelebre(false);
        }
        return realisateur;
    }

    @Override
    public int calculerDureeTotale(List<Film> filmList) {
        return filmList.stream().map(Film::getDuree).reduce((a,b)->(a+b)).get();
    }

    @Override
    public double calculerNoteMoyenne(double[] notes) {
        return ((double)round(Arrays.stream(notes).average().getAsDouble()*100))/100;
    }

    @Override
    public List<Realisateur> updateRealisateurCelebres(List<Realisateur> realisateurList) {
        return realisateurList.stream().filter(Realisateur::isCelebre).toList();
    }

    @Override
    public List<FilmDTO> findAllFilms() throws ServiceException {
        List<FilmDTO> liste = new ArrayList<>();
        liste = FilmMapper.convertFilmToFilmDTOs(filmDAO.findAll());
        return liste;
    }
}
