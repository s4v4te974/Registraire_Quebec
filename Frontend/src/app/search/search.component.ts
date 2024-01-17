import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchService } from '../service/search.service';
import { Secteur } from '../models/secteur';
import { Observable, Subject, catchError, map, merge, of, startWith } from 'rxjs';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { AsyncPipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Etablissement } from '../models/etablissement';
import { InterfaceMapperService } from '../service/interface-mapper.service';
import { EtabShort } from '../models/etab-short';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FormsModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    AsyncPipe,
    MatExpansionModule,
    MatPaginatorModule,
    MatTableModule
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  private nameFilterTrigger = new Subject<void>();
  searchControlValeur = new FormControl('');
  searchControlName = new FormControl('');
  etablissements: Etablissement[] = [];
  dataSourceEtab: any;
  codes: Secteur[] = [];
  shortEtabs: EtabShort[] = [];
  codesFilteredOptions: Observable<string[]> | undefined;
  namesFilteredOptions: Observable<string[]> | undefined;
  valuesCodesArray: string[] = [];
  valuesNamesArray: string[] = [];
  provideNeq: string = 'Please provide a least one value';
  names: string[] = [];
  etabLength: number = 0;
  displayedColumnsEtab: string[] = ['Nom Etablissement', 'Activité économique primaire', 'Activité économique secondaire', 'Adresse', 'Localité', 'Code postal'];

  constructor(private searchService: SearchService,
    private _snackBar: MatSnackBar, private mapper: InterfaceMapperService) { }

  ngOnInit(): void {
    this.getCodeData();
    this.getNomData();
  }

  //#region code

  getCodeData() {
    this.searchService.retrieveCodeValue()
      .pipe(
        catchError((error: any) => {
          return of([]);
        })
      )
      .subscribe((response: any) => {
        this.codes = response.map((item: { codDomVal: any; valDomFran: any; }) => ({
          code: item.codDomVal,
          valeur: item.valDomFran
        }));
        this.convertToArrayOfvaluesCode();
        this.setFilterOptionsValue();
      });
  }

  // private setFilterOptionsValue() {
  //   this.codesFilteredOptions = merge(
  //     this.searchControlName.valueChanges,
  //     this.nameFilterTrigger.asObservable()
  //   ).pipe(
  //     startWith(''),
  //     map(value => this._filterCode(value || '')),
  //   );
  // }

  private setFilterOptionsValue() {
    this.codesFilteredOptions = this.searchControlValeur.valueChanges.pipe(
      startWith(''),
      map(value => this._filterCode(value || '')),
    );
  }

  private _filterCode(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.valuesCodesArray.filter(option => option.toLowerCase().includes(filterValue)).slice(0, 20);
  }

  private convertToArrayOfvaluesCode() {
    if (this.codes) {
      for (const value of this.codes) {
        if (value && value.valeur) {
          this.valuesCodesArray.push(value.valeur);
        }
      }
    }
  }

  private retrieveCodeFromValue(value: string) {
    let codeSecteur = this.codes.find(code => code.valeur === value);
    return codeSecteur?.code;
  }

  //#endregion code


  //#region names
  getNomData() {
    this.searchService.retrieveNomData()
      .pipe(
        catchError((error: any) => {
          return of([]);
        })
      )
      .subscribe((response: any) => {
        this.shortEtabs = response.map((item: { combinedValues: any; nomEtab: any; }) => ({
          combinedValues: item.combinedValues,
          nomEtab: item.nomEtab
        }));
        this.convertToArrayOfvaluesName();
        this.setFilterOptionsName();
      });
  }

  private _filterName(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.valuesNamesArray.filter(option => option.toLowerCase().includes(filterValue)).slice(0, 20);
  }

  private setFilterOptionsName() {
    this.namesFilteredOptions = this.searchControlName.valueChanges.pipe(
      startWith(''),
      map(value => this._filterName(value || '')),
    );
  }

  private convertToArrayOfvaluesName() {
    if (this.shortEtabs) {
      for (const value of this.shortEtabs) {
        if (value && value.nomEtab) {
          this.valuesNamesArray.push(value.nomEtab);
        }
      }
    }
  }

  //#endregion names

  //#region submit
  onSubmit() {
    const codeValeur = this.searchControlValeur.value;
    const nameEtab = this.searchControlName.value;
    let code: number = -1;
    let name: string = '';

    if (codeValeur == '' && nameEtab == '') {
      this.openSnackBar(this.provideNeq);
      return;
    }

    if (codeValeur !== null) {
      code = this.retrieveCodeFromValue(codeValeur) ?? -1;
    }

    if (nameEtab !== null) {
      name = nameEtab ?? '';
    }
    this.getSelectedValue(name, code);
  }

  getSelectedValue(name: string, code: number) {
    this.searchService.retrieveSelectedEtabs(name, code)
      .pipe(
        catchError((error: any) => {
          this.openSnackBar(this.provideNeq);
          return of([])
        })
      ).subscribe((response: any) => {
        const etablissements: Etablissement[] = response.map((etabData?: any) => {
          return this.mapper.mapEtablissement(etabData);
        }) || [];
        this.etablissements = etablissements;
        this.dataSourceEtab = new MatTableDataSource<Etablissement>(etablissements);
        this.dataSourceEtab.paginator = this.paginator;
        this.etabLength = etablissements.length;
      })
  }

  //#endregion submit

  openSnackBar(message: string) {
    this._snackBar.open(message, 'close');
  }

  //#region dynamic change etab
  // public dynamicChangeValueName(value: any): string[] {

  //   const codeValue = value != null ? this.retrieveCodeFromValue(value) ?? -1 : 1;

  //   const etabsCodes: EtabShort[] = this.shortEtabs.filter(etab => {
  //     const numericValues: number[] = (etab.combinedValues?.split(',') || [])
  //       .map(value => parseInt(value, 10)).filter(value => !isNaN(value));
  //     return numericValues.includes(codeValue);
  //   });

  //   console.info('length', etabsCodes.length);

  //   const filteredValues: string[] = etabsCodes.filter(etab => etab?.nomEtab !== undefined).map(etab => etab!.nomEtab!);

  //   this.valuesNamesArray = filteredValues;
  //   this.nameFilterTrigger.next();

  //   return this.valuesNamesArray.slice(0, 20);
  // }
  //#endregion

  //#region dynamic change etab
  // public dynamicChangeValueCode(value: any): string[] {
  //   const codeValue = value != null ? this.retrieveCodeFromValue(value) ?? -1 : 1;

  //   const etabsCodes: EtabShort[] = this.shortEtabs.filter(etab => {
  //     const numericValues: number[] = (etab.combinedValues?.split(',') || [])
  //       .map(value => parseInt(value, 10)).filter(value => !isNaN(value));
  //     return numericValues.includes(codeValue);
  //   });

  //   if(etabsCodes.length !== 0){
  //     const filteredValues: string[] = etabsCodes.filter(etab => etab?.nomEtab !== undefined).map(etab => etab!.nomEtab!);

  //     this.valuesNamesArray = filteredValues;
  //     this.nameFilterTrigger.next();

  //     console.log('in if', this.valuesNamesArray[0]);

  //     return this.valuesNamesArray.slice(0, 20);
  //   }
  //   this.nameFilterTrigger.next();
  //   console.log('out if', this.valuesNamesArray[0]);
  //   this.valuesNamesArray = this.valuesNamesArrayDefault.filter(option => option.toLowerCase().includes('')).slice(0, 20);
  //   return this.valuesNamesArray;
  // }
  //#endregion
}