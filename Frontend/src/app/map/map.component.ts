import { Component, OnInit } from '@angular/core';
import { MapsService } from '../service/maps.service';
import { EtabMap } from '../models/etab-map';
import { Observable, catchError, map, of, startWith, tap } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InterfaceMapperService } from '../service/interface-mapper.service';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AsyncPipe } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MapdialogComponent } from '../mapdialog/mapdialog.component';

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [FormsModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    AsyncPipe,
    MatInputModule,
    ReactiveFormsModule,
    MatDialogModule],
  templateUrl: './map.component.html',
  styleUrl: './map.component.css'
})
export class MapComponent implements OnInit {

  errorService: string = 'Unable to call WS';
  missingInput: string = 'please provide all inputs';
  errorDialog: string = 'unable to calculate coordinate';
  etabMaps: EtabMap[] = [];
  etabsMapsSelected: EtabMap[] = [];
  currentLocation: string = '';
  searchAvailableLocations = new FormControl('');
  availableLocations: string[] = [];
  availableLocationsFilteredOptions: Observable<string[]> | undefined;
  searchValuesControl = new FormControl('');
  valueCodes: string[] = [];
  codesFilteredOptions: Observable<string[]> | undefined;
  searchEtabsControl = new FormControl('');
  namesFilteredOptions: Observable<string[]> | undefined;
  etabs: string[] = [];

  constructor(private mapService: MapsService, private _snackBar: MatSnackBar,
    private mapper: InterfaceMapperService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.retrieveMapData();
  }

  retrieveMapData() {
    this.mapService.retrieveAllLocations()
      .pipe(
        catchError((error: any) => {
          this.openSnackBar(this.errorService);
          return of([]);
        }),
        tap((response?: any) => {
          const etabs: EtabMap[] = response.map((etab?: any) => {
            return this.mapper.mapToEtabMap(etab);
          }) || [];
          this.etabMaps = etabs;
          this.populateDatas();
          this.setFilterOptionsEtabs();
          this.setFilterOptionsCodes();
          this.setFilterOptionsAvailableLocations();
        })
      )
      .subscribe();
  }

  private populateDatas() {
    let mySet = new Set<string>();
    if (this.etabMaps) {
      for (const value of this.etabMaps) {
        if (value && value.codeValue && value.nomEtab && value.zipcode && value.localite) {
          mySet.add(value.codeValue);
          this.etabs.push(value.nomEtab + '; ' + value.zipcode);
          this.availableLocations.push(value.zipcode + '; ' + value.localite);
        }
      }
    }
    this.valueCodes = Array.from(mySet);
  }

  //#region etabs
  private _filterEtabs(value: string): string[] {
    const filterValue: string = value.toLowerCase();
    return this.etabs.filter(option => option.toLowerCase().includes(filterValue)).slice(0, 20);
  }

  private setFilterOptionsEtabs() {
    this.namesFilteredOptions = this.searchEtabsControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filterEtabs(value || '')),
    );
  }
  //#endregion

  //#region codes
  private _filterCodes(value: string): string[] {
    const filterValue: string = value.toLowerCase();
    return this.valueCodes.filter(option => option.toLowerCase().includes(filterValue)).slice(0, 20);
  }

  private setFilterOptionsCodes() {
    this.codesFilteredOptions = this.searchValuesControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filterCodes(value || '')),
    );
  }
  //#endregion

  //#region availableLocations
  private _filterAvailableLocations(value: string): string[] {
    const filterValue: string = value.toLowerCase();
    return this.availableLocations.filter(option => option.toLowerCase().includes(filterValue)).slice(0, 20);
  }

  private setFilterOptionsAvailableLocations() {
    this.availableLocationsFilteredOptions = this.searchAvailableLocations.valueChanges.pipe(
      startWith(''),
      map(value => this._filterAvailableLocations(value || '')),
    );
  }
  //#endregion

  onSubmit() {
    if (!this.searchAvailableLocations.value || !this.searchValuesControl.value) {
      this.openSnackBar(this.missingInput);
    } else {
      const find: string[] = this.searchAvailableLocations.value.split('; ');
      const currentLocation: EtabMap | undefined = this.etabMaps.find(option => option?.zipcode?.toString() === find[0] && option.localite === find[1]);
      const etabsMapsByCode: EtabMap[] = this.etabMaps.filter((option) => option.codeValue === this.searchValuesControl.value);

      if (currentLocation === undefined) {
        this.openSnackBar(this.errorDialog);
      } else {
        const retainedLocation: EtabMap[] = this.filterDistance(currentLocation, etabsMapsByCode);
        this.openDialog(retainedLocation);
      }
    }
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, 'close');
  }

  openDialog(retainedLocation: EtabMap[]) {
    this.dialog.open(MapdialogComponent, {
      data: {
        items: retainedLocation,
      },
    });
  }

  filterDistance(currentLocation: EtabMap, EtabsMapsByCode: EtabMap[]): EtabMap[] {
    currentLocation.distance = 0;
    let tab = new Set<EtabMap>();
    tab.add(currentLocation)
    for (let etab of EtabsMapsByCode) {
      let distance = this.calculateDistance(currentLocation, etab);
      if (distance <= 25) {
        etab.distance = distance;
        tab.add(etab);
      }
    }
    return Array.from(tab);
  }

  calculateDistance(currentLocation: EtabMap, etab: EtabMap): number {
    const R = 6371;
    if (currentLocation.xCoordinate !== undefined && currentLocation.yCoordinate !== undefined
      && etab.xCoordinate !== undefined && etab.yCoordinate !== undefined) {

      const centralLatRad = this.toRadians(currentLocation.yCoordinate!);
      const destLatRad = this.toRadians(etab.yCoordinate!);

      const dLat = this.toRadians(etab.yCoordinate! - currentLocation.yCoordinate!);
      const dLon = this.toRadians(etab.xCoordinate! - currentLocation.xCoordinate!);

      const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(centralLatRad) * Math.cos(destLatRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      return R * c;
    } else {
      return 100;
    }
  }

  toRadians(degrees: number): number {
    return degrees * (Math.PI / 180);
  }
}
