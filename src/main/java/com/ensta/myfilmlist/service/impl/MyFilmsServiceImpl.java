package com.ensta.myfilmlist.service.impl;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.dao.impl.JdbcFilmDAO;
import com.ensta.myfilmlist.dao.impl.JdbcRealisateurDAO;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.mapper.FilmMapper;
import com.ensta.myfilmlist.mapper.RealisateurMapper;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import com.ensta.myfilmlist.service.MyFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.round;

@Service
public class MyFilmsServiceImpl implements MyFilmsService {
    public static final int NB_FILMS_MIN_REALISATEUR_CELEBRE = 3;

    @Autowired
    FilmDAO filmDAO;
    @Autowired
    RealisateurDAO realisateurDAO;

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

    @Override
    public FilmDTO createFilm(FilmForm filmForm) throws ServiceException {
        Optional<Realisateur> optionalRealisateur = realisateurDAO.findById(filmForm.getRealisateurId());
        if(optionalRealisateur.isPresent()) {
            Film film = FilmMapper.convertFilmFormToFilm(filmForm);
            film.setRealisateur(optionalRealisateur.get());
            return FilmMapper.convertFilmToFilmDTO(
                    filmDAO.save(film));
        }
        throw new ServiceException("Non Existent Real");
    }

    @Override
    public List<RealisateurDTO> findAllRealisateurs() throws ServiceException {
        return RealisateurMapper.convertRealisateursToRealisateurDTOs(realisateurDAO.findAll());
    }

    @Override
    public RealisateurDTO findRealisateurByNomAndPrenom(String nom, String prenom) throws ServiceException {
            Realisateur realisateur = realisateurDAO.findByNomAndPrenom(nom, prenom);
            if(realisateur==null) {
                return null;
            }
            return RealisateurMapper.convertRealisateurToRealisateurDTO(realisateur);
    }

    @Override
    public FilmDTO findFilmById(long id) throws ServiceException {
        Optional<Film> optFilmDTO = filmDAO.findById(id);
        if(optFilmDTO.isPresent()) {
            return FilmMapper.convertFilmToFilmDTO(optFilmDTO.get());
        }
        return null;
    }

    @Override
    public void deleteFilm(long id) throws ServiceException {
        Optional<Film> optFilm = filmDAO.findById(id);
        if(optFilm.isPresent()) {
            filmDAO.delete(optFilm.get());
        } else {
            throw new ServiceException("Non Existent Film");
        }
    }
}