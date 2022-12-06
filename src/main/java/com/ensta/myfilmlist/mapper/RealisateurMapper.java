package com.ensta.myfilmlist.mapper;

import com.ensta.myfilmlist.dto.RealisateurDTO;
import com.ensta.myfilmlist.model.Realisateur;

import java.util.List;
import java.util.stream.Collectors;

public class RealisateurMapper {
    public static RealisateurDTO convertRealisateurToRealisateurDTO(Realisateur r) {
        RealisateurDTO rDTO = new RealisateurDTO();
        rDTO.setId(r.getId());
        rDTO.setNom(r.getNom());
        rDTO.setPrenom(r.getPrenom());
        rDTO.setCelebre(r.isCelebre());
        rDTO.setDateNaissance(r.getDateNaissance());
        return rDTO;
    }

    public static List<RealisateurDTO> convertRealisateursToRealisateurDTOs(List<Realisateur> realisateurs) {
        return realisateurs.stream().
                map(RealisateurMapper::convertRealisateurToRealisateurDTO).
                collect(Collectors.toList());
    }

    public static Realisateur convertRealisateurDTOToRealisateur(RealisateurDTO rDTO) {
        Realisateur r = new Realisateur();
        r.setId(rDTO.getId());
        r.setPrenom(rDTO.getPrenom());
        r.setNom(rDTO.getNom());
        r.setCelebre(rDTO.isCelebre());
        r.setDateNaissance(rDTO.getDateNaissance());
        return r;
    }

    public static List<Realisateur> convertRealisateurDTOsToRealisateurs(List<RealisateurDTO> realisateurDTOs) {
        return realisateurDTOs.stream().
                map(RealisateurMapper::convertRealisateurDTOToRealisateur).
                collect(Collectors.toList());
    }
}
