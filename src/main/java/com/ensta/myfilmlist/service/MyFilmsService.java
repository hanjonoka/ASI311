package com.ensta.myfilmlist.service;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.form.FilmForm;
import com.ensta.myfilmlist.model.Film;
import com.ensta.myfilmlist.model.Realisateur;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public interface MyFilmsService {
    /**
     * @param realisateur
     * @return
     * @throws ServiceException
     */
    public Realisateur updateRealisateurCelebre(Realisateur realisateur) throws ServiceException;

    /**
     * @param filmList
     * @return
     */
    public int calculerDureeTotale(List<Film> filmList);

    /**
     * @param notes
     * @return
     */
    public double calculerNoteMoyenne(double[] notes);

    public List<Realisateur> updateRealisateurCelebres(List<Realisateur> realisateurList);

    public List<FilmDTO> findAllFilms() throws ServiceException;

    public FilmDTO createFilm(FilmForm filmForm) throws ServiceException;

    public List<RealisateurDTO> findAllRealisateurs() throws ServiceException;

    public RealisateurDTO findRealisateurByNomAndPrenom(String nom, String prenom) throws ServiceException;

    public FilmDTO findFilmById(long id) throws ServiceException;

    public void deleteFilm(long id) throws ServiceException;
}
