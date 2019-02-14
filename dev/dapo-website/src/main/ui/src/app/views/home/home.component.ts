import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "../../modules/core";
import { ConfigEvent } from "../../modules/core";
import { RealEstateService } from "../../modules/dapo/service/real-estate.service";
import { ConfigurationService } from "../../modules/core/configuration/configuration.service";
import { AuthenticationService } from "../../modules/core/authentication/authentication.service";
import { NomenclatureService } from "../../modules/dapo/service/nomenclature.service";
import { FormControl } from "@angular/forms";
import { Observable } from "rxjs";
import { startWith, map } from "rxjs/internal/operators";
import { City } from "../../modules/dapo/model/v1/city";
import { RealEstate } from "../../modules/dapo/model/v1/real-estate";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

  config: any;
  properties: Array<RealEstate>;
  propertyTypes: Array<string> = [];
  citiesFormControl = new FormControl();
  cities: City[] = [{id:1, name:"asd"}];
  filteredCities: City[];
  selectedCity: City;
  selectedPropType: string;
  request:any = {announcementType: 'SELL'};

  public zoom: number = 8;

  constructor(public broadcaster: Broadcaster,
              public nomenclatureService: NomenclatureService,
              public configService: ConfigurationService,
              public authenticationService: AuthenticationService,
              public realEstateService: RealEstateService) {
  }

  ngOnInit() {
    this.broadcaster.$on(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY).subscribe((result) => {
      this.config = result.data;
    });
    if (this.configService.isResolved) {
      this.config = this.configService.config;
    }

    this.nomenclatureService.getPropertyTypes().subscribe(result => {
      this.propertyTypes = result
    });

    this.nomenclatureService.cities.subscribe(result => {
      this.cities = result;
      this.filteredCities = result;
    });
  }

  public onCityChange(event) {
    this._filter(event.target.value);
  }

  public onCitySet($event) {
    if($event.target.value.length == 0) delete this.request["city"];
  }

  public _filter(value: string) {
    this.filteredCities =  this.cities.filter(city => city.name.toLowerCase().includes(value.toLowerCase()));
  }

  onBoundsChange($event) {
    //console.log(GeoUtils.parseGmapEvent($event));
  }

  onZoomChange($event) {
    this.zoom = $event;
  }

  cityNameFn(event):string {
    return event.name;
  }

  search(){
    this.realEstateService.search(this.request).subscribe(result => this.properties = result);
  }

}
