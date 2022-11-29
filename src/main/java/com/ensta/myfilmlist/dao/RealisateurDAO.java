package com.ensta.myfilmlist.dao;

import com.ensta.myfilmlist.model.Realisateur;

import java.util.List;
import java.util.Optional;

public interface RealisateurDAO {
    public List<Realisateur> findAll();
    Realisateur findByNomAndPrenom(String nom, String prenom);
    Optional<Realisateur> fundById(long id);
}
