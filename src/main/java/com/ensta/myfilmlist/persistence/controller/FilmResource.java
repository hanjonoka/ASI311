package com.ensta.myfilmlist.persistence.controller;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.form.FilmForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

public interface FilmResource {
    ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException;
    ResponseEntity<FilmDTO> getFilmById(@PathVariable long id) throws ControllerException;
    @Transactional
    ResponseEntity<FilmDTO> createFilm(@Valid FilmForm filmForm) throws ControllerException;
    @Transactional
    ResponseEntity<?> deleteFilm(@PathVariable long id) throws ControllerException;
}
