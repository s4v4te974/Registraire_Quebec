import { Component, ViewChild } from '@angular/core';
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
import { InterfaceMapperService } from '../service/interface-mapper.service';


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
  entreprise?: Entreprise =  {}
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
    private _snackBar: MatSnackBar, private mapper: InterfaceMapperService) { }
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
            return this.mapper.mapEtablissement(etabData);
          }) || [];
          this.dataSourceEtab = new MatTableDataSource<Etablissement>(etablissements);
          this.dataSourceEtab.paginator = this.paginator;
          this.etabLength = etablissements.length;
          //#endregion etablissement

          //#region fusionScission
          const fusionsScissions: Fusci[] = response.fusionScissions?.map((fusci: any) => {
            return this.mapper.mapFusionsScissions(fusci);
          }) || [];
          this.dataSourceFusci = new MatTableDataSource<Fusci>(fusionsScissions || []);
          //#endregion fusionScission

          //#region noms
          const noms: Nom[] = response.noms?.map((nom: any) => {
            return this.mapper.mapNom(nom);
          }) || [];
          this.dataSourceNoms = new MatTableDataSource<Nom>(noms || []);
          //#endregion nom

          //#region contiTransfo
          const contiTransfo: ContiTransfo[] = response.contiTransfo?.map((contiTransfo: any) => {
            return this.mapper.mapContiTransfo(contiTransfo);
          }) || [];
          this.dataSourceContiTransfo = new MatTableDataSource<ContiTransfo>(contiTransfo || []);
          //#endregion contiTransfo

          //#region entreprise
          this.entreprise = this.mapper.mapEntreprise(response);
          this.entreprise.etablissements = etablissements;
          this.entreprise.contiTransfo = contiTransfo;
          this.entreprise.fusionScissions = fusionsScissions;
          this.entreprise.noms = noms;
          }
          
          //#endregion entreprise
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
