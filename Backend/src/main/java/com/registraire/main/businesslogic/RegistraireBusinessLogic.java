package com.registraire.main.businesslogic;

import com.registraire.main.mapper.DomaineValeurMapper;
import com.registraire.main.mapper.EntrepriseMapper;
import com.registraire.main.mapper.EtablissementMapper;
import com.registraire.main.models.dto.DomaineValeurRecord;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.dto.EtablissementRecord;
import com.registraire.main.models.entities.DomaineValeur;
import com.registraire.main.models.entities.Entreprise;
import com.registraire.main.models.entities.Etablissement;
import com.registraire.main.repository.DomaineValeurRepo;
import com.registraire.main.repository.EntrepriseRepo;
import com.registraire.main.repository.EtablissementRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistraireBusinessLogic {

    private final EntrepriseRepo entrepriseRepo;

    private final DomaineValeurRepo domaineValeurRepo;

    private final EtablissementRepo etablissementRepo;

    private static final DomaineValeurMapper domaineValeurMapper = DomaineValeurMapper.INSTANCE;

    private static final EntrepriseMapper entrepriseMapper = EntrepriseMapper.INSTANCE;

    private static final EtablissementMapper etablissementMapper = EtablissementMapper.INSTANCE;


    public EntrepriseRecord retrieveEntreprise(String neq) {
        Entreprise entreprise = entrepriseRepo.findById(neq).orElse(null);
        return entrepriseMapper.mapToEntrepriseRecord(entreprise);
    }

    public List<DomaineValeurRecord> retrieveDomaineValeur() {
        List<DomaineValeur> domaineValeurs = domaineValeurRepo.findAll();
        return domaineValeurs.stream().map(domaineValeurMapper::mapToDomaineRecord)
                .toList();
    }

    public List<String> retrieveAllEtabNames() {
        List<String> defaultNames = etablissementRepo.retrieveEtabNames();
        return defaultNames.stream().distinct().toList();
    }

    public List<EtablissementRecord> retrieveSelectedEtab(String nom, Integer code) {
        if(code == -1){
            List<Etablissement> etabs = etablissementRepo.findByNomEtab(nom);
            return etabs.stream().map(etablissementMapper::mapToEtablissementRecord).toList();
        }else if(nom.isBlank()){
            List<Etablissement> etabs = etablissementRepo.findByCodActEconOrCodActEcon2(code, code);
            return etabs.stream().map(etablissementMapper::mapToEtablissementRecord).toList();
        }else{
            List<Etablissement> etabs =
                    etablissementRepo.findByNamesAndCodes(nom, code);
            return etabs.stream().map(etablissementMapper::mapToEtablissementRecord).toList();
        }
    }
}