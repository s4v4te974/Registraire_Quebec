import { Component, OnInit } from '@angular/core';
import { SearchService } from '../service/search.service';
import { Secteur } from '../models/secteur';
import { Observable, catchError, map, of, startWith } from 'rxjs';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AsyncPipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FormsModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    AsyncPipe
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  searchControlValeur = new FormControl('');
  searchControlName = new FormControl('');
  codes: Secteur[] = [];
  codesFilteredOptions: Observable<string[]> | undefined;
  valuesArray: string[] = [];
  provideNeq: string = 'Please provide a least one value';

  constructor(private searchService: SearchService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getCodeData();
    this.getNomData();
  }

  getCodeData() {
    this.searchService.retrieveCodeValue()
      .pipe(
        catchError((error: any) => {
          console.log(error);
          return of([]);
        })
      )
      .subscribe((response: any) => {
        this.codes = response.map((item: { codDomVal: any; valDomFran: any; }) => ({
          code: item.codDomVal,
          valeur: item.valDomFran
        }));
        this.convertToArrayOfvalues();
        this.setFilterOptionsValue();
        this.setFilterOptionsName();
      });
  }

  getNomData(){
    
  }

  private _filter(value: string): string[]{
    const filterValue = value.toLowerCase();
    return this.valuesArray.filter(option => option.toLowerCase().includes(filterValue)).slice(0, 20);
  }

  private setFilterOptionsValue(){
    this.codesFilteredOptions = this.searchControlValeur.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private setFilterOptionsName(){
    this.codesFilteredOptions = this.searchControlName.valueChanges.pipe( 
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private convertToArrayOfvalues() {
    if(this.codes){
      for (const value of this.codes){
        if(value && value.valeur){
          this.valuesArray.push(value.valeur);
        } 
      }
    }
  }

  onSubmit(){
    const codeValeur = this.searchControlValeur.value;
    const name = this.searchControlName.value;

    if (codeValeur == '' && name == ' ') {
      this.openSnackBar(this.provideNeq);
    }
    console.log(codeValeur)
    console.log(name);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, 'close');
  }
}