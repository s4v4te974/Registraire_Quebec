package com.registraire.main.businesslogic;

import com.registraire.main.mapper.CodeEtabMapper;
import com.registraire.main.mapper.EntrepriseMapper;
import com.registraire.main.mapper.EtabShortMapper;
import com.registraire.main.mapper.EtablissementMapper;
import com.registraire.main.mapper.GeoCodeurMapper;
import com.registraire.main.models.dto.basic.EntrepriseRecord;
import com.registraire.main.models.dto.basic.EtablissementRecord;
import com.registraire.main.models.dto.view.CodeEtabRecord;
import com.registraire.main.models.dto.view.EtabShortRecord;
import com.registraire.main.models.dto.view.GeoCodeurRecord;
import com.registraire.main.models.entities.basic.Entreprise;
import com.registraire.main.models.entities.basic.Etablissement;
import com.registraire.main.models.entities.view.GeoCodeur;
import com.registraire.main.repository.CodeEtabRepo;
import com.registraire.main.repository.EntrepriseRepo;
import com.registraire.main.repository.EtabShortRepo;
import com.registraire.main.repository.EtablissementRepo;
import com.registraire.main.repository.GeoCodeurRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistraireBusinessLogic {

    private final EntrepriseRepo entrepriseRepo;

    private final EtablissementRepo etablissementRepo;

    private final CodeEtabRepo codeEtabRepo;

    private final EtabShortRepo etabShortRepo;

    private final GeoCodeurRepo geoCodeurRepo;

    private static final GeoCodeurMapper geoCodeurMapper = GeoCodeurMapper.INSTANCE;

    private static final CodeEtabMapper codeEtabMapper = CodeEtabMapper.INSTANCE;

    private static final EtabShortMapper etabShortMapper = EtabShortMapper.INSTANCE;

    private static final EntrepriseMapper entrepriseMapper = EntrepriseMapper.INSTANCE;

    private static final EtablissementMapper etablissementMapper = EtablissementMapper.INSTANCE;


    public EntrepriseRecord retrieveEntreprise(String neq) {
        Entreprise entreprise = entrepriseRepo.findById(neq).orElse(null);
        return entrepriseMapper.mapToEntrepriseRecord(entreprise);
    }

    public List<CodeEtabRecord> retrieveCodeEtabs() {
        return codeEtabRepo.findAll().stream().map(codeEtabMapper::mapToCodeEtabRecord).toList();
    }

    public List<EtabShortRecord> retrieveShortsEtab() {
        List<EtabShortRecord> etabs = etabShortRepo.findAll().stream().map(etabShortMapper::mapToEtabShort).toList();
        log.info("the size of the list : {}", etabs.size());
        return etabs;
    }

    public List<EtablissementRecord> retrieveSelectedEtab(String nom, Integer code) {
        if (code == -1) {
            List<Etablissement> etabs = etablissementRepo.findByNomEtab(nom);
            return etabs.stream().map(etablissementMapper::mapToEtablissementRecord).toList();
        } else if (nom.isBlank()) {
            List<Etablissement> etabs = etablissementRepo.findByCodActEconOrCodActEcon2(code, code);
            return etabs.stream().map(etablissementMapper::mapToEtablissementRecord).toList();
        } else {
            List<Etablissement> etabs =
                    etablissementRepo.findByNamesAndCodes(nom, code);
            return etabs.stream().map(etablissementMapper::mapToEtablissementRecord).toList();
        }
    }

    public List<GeoCodeurRecord> retrieveGeoCoded() {
        List<GeoCodeur> geos = geoCodeurRepo.findAll();
        return geos.stream().map(geoCodeurMapper::mapToGeoCodeurRecord).toList();
    }

}