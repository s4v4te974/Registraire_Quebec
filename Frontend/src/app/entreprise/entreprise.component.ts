import {  Component, ViewChild } from '@angular/core';
import { EntrepriseService } from '../service/entreprise.service';
import { Entreprise } from '../models/entreprise';
import { FormsModule } from '@angular/forms';
import { Etablissement } from '../models/etablissement';
import { Fusci } from '../models/fusci';
import { Nom } from '../models/nom';
import { ContiTransfo } from '../models/conti-transfo';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { catchError, of } from 'rxjs';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-entreprise',
  standalone: true,
  imports: [FormsModule,
    MatSnackBarModule, MatExpansionModule, MatPaginatorModule, MatTableModule,
  ],
  templateUrl: './entreprise.component.html',
  styleUrl: './entreprise.component.css'
})
export class EntrepriseComponent {

  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  //#region object
  entreprise?: Entreprise;
  //#endregion object


  //#region neq
  neq: string = '';
  provideNeq: string = 'Please provide a valid neq';
  wrongNeq: string = 'The neq provided is not ok';
  //#endregion neq

  //#region pagination
  dataSourceEtab: any;
  etabLength: number = 0;
  dataSourceFusci: any;
  dataSourceNoms: any;
  dataSourceContiTransfo: any;
  displayedColumnsEtab: string[] = ['Nom Etablissement', 'Activité économique primaire', 'Activité économique secondaire', 'Adresse', 'Localité', 'Code postal'];
  // displayedColumnsFusci: string[] = ['Nom Etablissement', 'Activité économique primaire', 'Activité économique secondaire', 'Adresse', 'Localité', 'Code postal'];
  // displayedColumnsNoms: string[] = ['Nom Etablissement', 'Activité économique primaire', 'Activité économique secondaire', 'Adresse', 'Localité', 'Code postal'];
  // displayedColumnsContiTransfo: string[] = ['Nom Etablissement', 'Activité économique primaire', 'Activité économique secondaire', 'Adresse', 'Localité', 'Code postal'];

  //#endregion pagination

  //#region snackbar
  panelOpenState: boolean = false;
  //#endregion snackbar


  constructor(private entrepriseService: EntrepriseService,
    private _snackBar: MatSnackBar) { }
  // example : 1140180291

  //#region service
  getEntreprise(value: string) {
    if (value === '') {
      this.openSnackBar(this.provideNeq);
    } else {
      const entreprise = this.entrepriseService.retrieveEntreprise(value)
        .pipe(
          catchError((error: any) => {
            console.log('welcome to error', error);
            return of([]);
          })
        ).subscribe((response: any) => {
          //#region etablissement
          const etablissements: Etablissement[] = response.etablissements?.map((etabData?: any) => {
            return {
              id: etabData?.id,
              neq: etabData?.neq,
              noSufEtab: etabData?.noSufEtab,
              indEtabPrinc: etabData?.indEtabPrinc,
              indSalonBronz: etabData?.indSalonBronz,
              indVenteTabacDetl: etabData?.indVenteTabacDetl,
              indDisp: etabData?.indDisp,
              lign1Adr: etabData?.lign1Adr,
              lign2Adr: etabData?.lign2Adr,
              lign3Adr: etabData?.lign3Adr,
              lign4Adr: etabData?.lign4Adr,
              codActEcon: etabData?.codActEcon,
              descActEconEtab: etabData?.descActEconEtab,
              noActEconEtab: etabData?.noActEconEtab,
              codActEcon2: etabData?.codActEcon2,
              descActEconEtab2: etabData?.descActEconEtab2,
              noActEconEtab2: etabData?.noActEconEtab2,
              nomEtab: etabData?.nomEtab,
              entreprise: null,
            }
          }) || [];
          this.dataSourceEtab = new MatTableDataSource<Etablissement>(etablissements);
          this.dataSourceEtab.paginator = this.paginator;
          this.etabLength = etablissements.length;
          //#endregion etablissement
          //#region fusionScission
          const fusionsScissions: Fusci[] = response.fusionScissions?.map((fuscis: any) => {
            return {
              id: fuscis.id,
              neq: fuscis.neq,
              neqAssujRel: fuscis.neqAssujRel,
              denomnSoc: fuscis.denomnSoc,
              codRelaAssuj: fuscis.codRelaAssuj,
              datEfctvt: fuscis.datEfctvt,
              indDisp: fuscis.indDisp,
              lign1Adr: fuscis.lign1Adr,
              lign2Adr: fuscis.lign2Adr,
              lign3Adr: fuscis.lign3Adr,
              lign4Adr: fuscis.lign4Adr,
              Entreprise: null
            };
          }) || [];
          this.dataSourceFusci = new MatTableDataSource<Fusci>(fusionsScissions || []);
          //#endregion fusionScission
          //#region noms
          const noms: Nom[] = response.noms?.map((nom: any) => {
            return {
              id: nom.id,
              neq: nom.neq,
              nomAssuj: nom.nomAssuj,
              nomAssujLangEtrng: nom.nomAssujLangEtrng,
              statNom: nom.statNom,
              typNomAssuj: nom.typNomAssuj,
              datInitNomAssuj: nom.datInitNomAssuj,
              datFinNomAssuj: nom.datFinNomAssuj,
              Entreprise: null
            };
          }) || [];
          this.dataSourceNoms = new MatTableDataSource<Nom>(noms || []);
          //#endregion nom
          //#region contiTransfo
          const contiTransfo: ContiTransfo[] = response.contiTransfo?.map((contiTransfo: any) => {
            return {
              id: contiTransfo.id,
              neq: contiTransfo.neq,
              codTypChang: contiTransfo.codTypChang,
              codRegimJuri: contiTransfo.codRegimJuri,
              autrRegimJuri: contiTransfo.autrRegimJuri,
              nomLoct: contiTransfo.nomLoct,
              datEfctvt: contiTransfo.datEfctvt,
              Entreprise: null
            };
          }) || [];
          this.dataSourceContiTransfo = new MatTableDataSource<ContiTransfo>(contiTransfo || []);
          //#endregion contiTransfo
          //#region entreprise
          this.entreprise = {
            neq: response.neq,
            indFail: response.indFail,
            datImmat: response.datImmat,
            codRegimJuri: response.codRegimJuri,
            codIntvalEmploQue: response.codIntvalEmploQue,
            datCessPrevu: response.datCessPrevu,
            codStatImmat: response.codStatImmat,
            codFormeJuri: response.codFormeJuri,
            datStatImmat: response.datStatImmat,
            codRegimJuriConsti: response.codRegimJuriConsti,
            datDepoDeclr: response.datDepoDeclr,
            anDecl: response.anDecl,
            anProd: response.anProd,
            datLimitProd: response.datLimitProd,
            anProdPre: response.anProdPre,
            datLimitProdPre: response.datLimitProdPre,
            datMajIndexNom: response.datMajIndexNom,
            codActEconCae: response.codActEconCae,
            noActEconAssuj: response.noActEconAssuj,
            descActEconAssuj: response.descActEconAssuj,
            codActEconCae2: response.codActEconCae2,
            noActEconAssuj2: response.noActEconAssuj2,
            descActEconAssuj2: response.descActEconAssuj2,
            nomLoctConsti: response.nomLoctConsti,
            datConsti: response.datConsti,
            indConvenUnmnActnr: response.indConvenUnmnActnr,
            indRetToutPouvr: response.indRetToutPouvr,
            indLimitResp: response.indLimitResp,
            datDebResp: response.datDebResp,
            datFinResp: response.datFinResp,
            objetSoc: response.objetSoc,
            noMtrVolont: response.noMtrVolont,
            adrDomclAdrDisp: response.adrDomclAdrDisp,
            adrDomclLign1Adr: response.adrDomclLign1Adr,
            adrDomclLign2Adr: response.adrDomclLign2Adr,
            adrDomclLign3Adr: response.adrDomclLign3Adr,
            adrDomclLign4Adr: response.adrDomclLign4Adr,
            etablissements: etablissements,
            contiTransfo: contiTransfo,
            fusionScissions: fusionsScissions,
            noms: noms
          }
          //#endregion entreprise
        }
        );
    }
  }

  //#endregion service

  //#region snackbar
  openSnackBar(message: string) {
    this._snackBar.open(message, 'close');
  }
  //#endregion snackbar
}
