import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css']
})
export class GoogleMapsComponent implements OnInit {
  title: string = 'My first AGM project';
  lng: number = 16.925168;
  lat: number = 52.406374;
  constructor() { }

  ngOnInit() {
  }

}
