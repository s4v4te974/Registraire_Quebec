package com.registraire.main.controller;

import com.registraire.main.businesslogic.RegistraireBusinessLogic;
import com.registraire.main.models.dto.DomaineValeurRecord;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.dto.EtablissementRecord;
import com.registraire.main.models.dto.NomRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RegistraireController {

    private final RegistraireBusinessLogic businessLogic;

    @GetMapping(value = "entreprises/")
    public ResponseEntity<EntrepriseRecord> retrieveEntreprise(
            @RequestParam String neq) {
        EntrepriseRecord entreprise = businessLogic.retrieveEntreprise(neq);
        if(entreprise == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(entreprise, HttpStatus.OK);
        }
    }

    @GetMapping(value = "code-valeurs/")
    public ResponseEntity<List<DomaineValeurRecord>> retrieveCodeValues(){
        List<DomaineValeurRecord> domaineValeurRecords = businessLogic.retrieveDomaineValeur();
        if(domaineValeurRecords.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(domaineValeurRecords, HttpStatus.OK);
        }
    }

    @GetMapping(value = "etabs/")
    public ResponseEntity<List<EtablissementRecord>> retrieveNames() {
        List<EtablissementRecord> etabs = businessLogic.retrieveAllEtab();
        if (etabs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(etabs, HttpStatus.OK);
        }
    }
 }
