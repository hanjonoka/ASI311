package com.ensta.myfilmlist.persistence.controller.impl;

import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.ControllerException;
import com.ensta.myfilmlist.exception.ServiceException;
import com.ensta.myfilmlist.persistence.controller.FilmResource;
import com.ensta.myfilmlist.service.MyFilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmResourceImpl implements FilmResource {

    @Autowired
    MyFilmsService myFilmsService;

    @Override
    @GetMapping()
    public ResponseEntity<List<FilmDTO>> getAllFilms() throws ControllerException {
        try {
            List<FilmDTO> listFilm = myFilmsService.findAllFilms();
            return ResponseEntity.ok(listFilm);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).build();
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable long id) throws ControllerException {
        try {
            FilmDTO f = myFilmsService.findFilmById(id);
            if(f!=null) {
                return ResponseEntity.ok(f);
            }else{
                return ResponseEntity.status(404).build();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException();
        }
    }

}
