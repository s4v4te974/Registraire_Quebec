package com.registraire.main.businesslogic;

import com.registraire.main.mapper.ContinuationTransformationMapper;
import com.registraire.main.mapper.DomaineValeurMapper;
import com.registraire.main.mapper.EntrepriseMapper;
import com.registraire.main.mapper.EtablissementMapper;
import com.registraire.main.mapper.FusionScissionMapper;
import com.registraire.main.mapper.NomMapper;
import com.registraire.main.models.dto.DomaineValeurRecord;
import com.registraire.main.models.dto.EntrepriseRecord;
import com.registraire.main.models.dto.EtablissementRecord;
import com.registraire.main.models.entities.DomaineValeur;
import com.registraire.main.models.entities.Entreprise;
import com.registraire.main.models.entities.Etablissement;
import com.registraire.main.repository.ContinuationTransformationRepo;
import com.registraire.main.repository.DomaineValeurRepo;
import com.registraire.main.repository.EntrepriseRepo;
import com.registraire.main.repository.EtablissementRepo;
import com.registraire.main.repository.FusionScissionRepo;
import com.registraire.main.repository.NomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistraireBusinessLogic {

    private final EntrepriseRepo entrepriseRepo;

    private final DomaineValeurRepo domaineValeurRepo;

    private final EtablissementRepo etablissementRepo;

    private final DomaineValeurMapper domaineValeurMapper = DomaineValeurMapper.INSTANCE;

    private final EntrepriseMapper entrepriseMapper = EntrepriseMapper.INSTANCE;

    private EtablissementMapper etablissementMapper = EtablissementMapper.INSTANCE;


    public EntrepriseRecord retrieveEntreprise(String neq) {
        Entreprise entreprise = entrepriseRepo.findById(neq).orElse(null);
        return entrepriseMapper.mapToEntrepriseRecord(entreprise);
    }

    public List<DomaineValeurRecord> retrieveDomaineValeur() {
        List<DomaineValeur> domaineValeurs = domaineValeurRepo.findAll();
        return domaineValeurs.stream().map(domaineValeurMapper::mapToDomaineRecord)
                .toList();
    }

    public List<EtablissementRecord> retrieveAllEtab() {
        List<Etablissement> names = etablissementRepo.findAll();
        return names.stream().map(etablissementMapper::mapToEtablissement).toList();
    }
}
