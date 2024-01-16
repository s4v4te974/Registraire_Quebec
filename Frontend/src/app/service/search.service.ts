import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Secteur } from '../models/secteur';
import { Etablissement } from '../models/etablissement';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  urlCode: string = 'http://localhost:8080/code-valeurs/';



  constructor(private httpClient: HttpClient) { }

  retrieveCodeValue() : Observable<Secteur>{
    return this.httpClient.get<Secteur>(this.urlCode);
  }

  // retrieveNomData() : Observable<Etablissement>{
  //   return null;
  // }
}
