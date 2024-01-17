import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Secteur } from '../models/secteur';
import { Etablissement } from '../models/etablissement';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  urlCode: string = 'http://localhost:8080/code-valeurs/';
  urlName: string = 'http://localhost:8080/etabs-names/';
  urlEtab: string = 'http://localhost:8080/selected-etabs/';

  constructor(private httpClient: HttpClient) { }

  retrieveCodeValue(): Observable<Secteur> {
    return this.httpClient.get<Secteur>(this.urlCode);
  }

  retrieveNomData(): Observable<string> {
    return this.httpClient.get<string>(this.urlName);
  }

  retrieveSelectedEtabs(name: string, code: number, ): Observable<Etablissement> {
    const httpParams = new HttpParams().set('nom', name).set('code', code);
    console.log(httpParams);
    return this.httpClient.get<Etablissement>(this.urlEtab, { params: httpParams });
  }
}
