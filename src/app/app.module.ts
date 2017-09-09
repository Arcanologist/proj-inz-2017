import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FileSelectDirective, FileDropDirective } from 'ng2-file-upload';


import { AppComponent } from './app.component';
import { DragUploadInput } from './upload/upload.component';
import {RouterModule,  Routes} from "@angular/router";
import { HomeComponent } from './home/home.component';
import { FileUploadModule } from 'ng2-file-upload';
import { GoogleMapsComponent } from './google-maps/google-maps.component';
import { FormsModule } from '@angular/forms';
import {  ApplicationRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgmCoreModule } from '@agm/core';
//import { ScrollComponent } from './scroll/scroll.component';
import { VirtualScrollModule } from 'angular2-virtual-scroll';
import { HttpModule } from '@angular/http';
import { ListItemComponent } from './lists/list-item.component';
import { ListWithApiComponent } from './lists/list-with-api.component';
import { MultiColListComponent } from './lists/multi-col-list.component';
import { TableListComponent } from './lists/table-list.component';
import { VerticalListComponent } from './lists/vertical-list.component';
import{SrcollComponent}from './scroll/scroll.component';
const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },

  {
    path: 'upload',
    component:DragUploadInput,

  },
  { path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  { path: '**',redirectTo:'/home',pathMatch:'full' }
];
@NgModule({
  declarations: [
    SrcollComponent,
    AppComponent,
    DragUploadInput,
    HomeComponent,
    GoogleMapsComponent,
    ListItemComponent,
    ListWithApiComponent,
    MultiColListComponent,
    TableListComponent,
    VerticalListComponent

  ],
  imports: [
    BrowserModule,
    FileUploadModule,
    VirtualScrollModule,



    FormsModule,
    HttpModule,

    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),   CommonModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyC9OZ7qMMudsBHrExocFAMVFR2hsGjvIGE' //Key do google maps
    })
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
