import { AfterViewInit, Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle } from '@angular/material/dialog';
import * as L from 'leaflet';
import { EtabMap } from '../models/etab-map';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-mapdialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, FormsModule],
  templateUrl: './mapdialog.component.html',
  styleUrl: './mapdialog.component.css'
})
export class MapdialogComponent implements AfterViewInit, OnInit {

  
  selectedDistance: number = 5;
  map?: L.Map;
  markers: { coords: number[]; content: string; distance: number }[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: { items: EtabMap[] }) { }

  ngOnInit(): void {
    for (const key in this.data.items) {
      if (this.data.items.hasOwnProperty(key)) {
        const etab = this.data.items[key];
        if (etab?.adresse !== null && etab?.adresse !== undefined
          && etab?.distance !== null && etab?.distance !== undefined
          && etab?.xCoordinate !== null && etab?.xCoordinate !== undefined
          && etab?.yCoordinate !== null && etab?.yCoordinate !== undefined
        ) {
          this.markers.push({
            coords: [etab.yCoordinate, etab.xCoordinate],
            content: etab?.adresse,
            distance: etab?.distance,
          })
        }
      }
    }
  }

  ngAfterViewInit(): void {
    this.initMap();
    this.filterMarkers();
    L.Icon.Default.imagePath = 'assets/';
  }

  initMap() {
    const zero = this.data.items.find(option => option.distance === 0);
    this.map = L.map('leafletMap').setView([zero?.yCoordinate!, zero?.xCoordinate!], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(this.map);

    this.updateMarkers();
  }

  filterMarkers(): void {
    const filteredMarkers = this.markers.filter(marker => marker.distance <= this.selectedDistance);
    this.updateMarkers(filteredMarkers);
  }


  updateMarkers(filteredMarkers?: { coords: number[]; content: string; distance: number }[]): void {
    this.map?.eachLayer(layer => {
      if (layer instanceof L.Marker) {
        this.map?.removeLayer(layer);
      }
    });

    const markersToDisplay = filteredMarkers || this.markers;

    markersToDisplay.forEach(marker => {
      if (this.map) {
        L.marker(marker.coords as L.LatLngTuple)
          .bindPopup(marker.content)
          .addTo(this.map);
      }
    });
  }

  onDistanceChange(): void {
    this.filterMarkers();
  }
}
