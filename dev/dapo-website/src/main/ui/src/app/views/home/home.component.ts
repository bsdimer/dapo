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

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {

  config: any;
  latestProperties: any;
  selectedPropType: any = "";
  propertyTypes: Array<string> = [];
  citiesFormControl = new FormControl();
  cities: City[] = [{id:1, name:"asd"}];
  filteredCities: City[];
  selectedCity: City;

  private zoom: number = 8;

  constructor(private broadcaster: Broadcaster,
              private nomenclatureService: NomenclatureService,
              private configService: ConfigurationService,
              private authenticationService: AuthenticationService,
              private realEstateService: RealEstateService) {
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

  private onCityChange(event) {
    this._filter(event.target.value);
  }

  private _filter(value: string) {
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
    debugger;
  }

}
