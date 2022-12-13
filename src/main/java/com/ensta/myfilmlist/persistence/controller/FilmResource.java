package com.ensta.myfilmlist.persistence.controller;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmResource {
    ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException;
    ResponseEntity<FilmDTO> getFilmById() throws ControllerException;
}
