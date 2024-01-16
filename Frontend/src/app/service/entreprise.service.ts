import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable} from 'rxjs';
import { Entreprise } from '../models/entreprise';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {

  url: string = 'http://localhost:8080/entreprises/'

  constructor(private httpClient: HttpClient) { }

  public retrieveEntreprise(neq: string): Observable<Entreprise> {
    const httpParams = new HttpParams().set('neq', neq);
    return this.httpClient.get<Entreprise>(this.url, { params: httpParams });
  }
}