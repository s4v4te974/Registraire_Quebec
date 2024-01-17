import { Routes } from '@angular/router';
import { EntrepriseComponent } from './entreprise/entreprise.component';
import { HomeComponent } from './home/home.component';
import { SearchComponent } from './search/search.component';
import { MapComponent } from './map/map.component';

export const routes: Routes = [
    {path: '', component: HomeComponent, title: "home"},
    {path: 'entreprise-by-neq', component: EntrepriseComponent, title: 'search entreprise by neq'},
    {path: 'search', component: SearchComponent, title: 'search by name and activity sector'},
    {path: 'maps', component: MapComponent, title: 'search around me'}
];
