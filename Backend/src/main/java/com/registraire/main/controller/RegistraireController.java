package com.registraire.main.controller;

import com.registraire.main.businesslogic.RegistraireBusinessLogic;
import com.registraire.main.models.dto.CodeEtabRecord;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.dto.EtabShortRecord;
import com.registraire.main.models.dto.EtablissementRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistraireController {

    private final RegistraireBusinessLogic businessLogic;

    @GetMapping(value = "entreprises/")
    public ResponseEntity<EntrepriseRecord> retrieveEntreprise(
            @RequestParam String neq) {
        EntrepriseRecord entreprise = businessLogic.retrieveEntreprise(neq);
        if (entreprise == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(entreprise, HttpStatus.OK);
        }
    }

    @GetMapping(value = "code-valeurs/")
    public ResponseEntity<List<CodeEtabRecord>> retrieveCodeValues() {
        List<CodeEtabRecord> domaineValeurRecords = businessLogic.retrieveCodeEtabs();
        if (domaineValeurRecords.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(domaineValeurRecords, HttpStatus.OK);
        }
    }

    @GetMapping(value = "etabs-names/")
    public ResponseEntity<List<EtabShortRecord>> retrieveNames() {
        List<EtabShortRecord> etabs = businessLogic.retrieveShortsEtab();
        if (etabs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(etabs, HttpStatus.OK);
        }
    }

    @GetMapping(value = "selected-etabs/")
    public ResponseEntity<List<EtablissementRecord>> retrieveSelectedEtabs(
            @RequestParam(required = false, defaultValue = "") String nom,
            @RequestParam(required = false, defaultValue = "-1") Integer code
    ) {
        List<EtablissementRecord> etabs = businessLogic.retrieveSelectedEtab(nom, code);
        if (etabs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(etabs, HttpStatus.OK);
        }
    }

}
