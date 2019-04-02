import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {RealEstate} from "../../modules/dapo/model/v1/real-estate";
import {RealEstateType} from "../../modules/dapo/model/v1/real-estate-type.enum";
import {EnumUtils} from "../../modules/core/utils/enum-utils";
import {ConfigurationService} from "../../modules/core/configuration/configuration.service";
import {Broadcaster} from "../../modules/core/events/broadcaster";
import {NomenclatureService} from "../../modules/dapo/service/nomenclature.service";
import {AuthenticationService} from "../../modules/core/authentication/authentication.service";
import {RealEstateService} from "../../modules/dapo/service/real-estate.service";
import {ConfigEvent} from "../../modules/core/configuration/config-event.enum";
import {FormControl} from "@angular/forms";
import {City} from "../../modules/dapo/model/v1/city";
import {Country} from "../../modules/dapo/model/v1/country";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AdvancedSearchComponent implements OnInit {

  config: any;
  result: Array<RealEstate> = [];
  countries: Array<Country> = [];
  cities: Array<City> = [];
  properties: Array<RealEstate>;
  propertyTypes: Array<string> = [];
  citiesFormControl = new FormControl();

  selectedCities: Array<City> = [];
  filteredCities: Array<City> = [];

  selectedCountry: string;
  filtredCountries: Array<Country> = [];

  selectedPropType: string;
  request: any = {announcementType: 'SELL'};

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
      console.log(this.config);
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
    this.nomenclatureService.countries.subscribe(result => {
      this.countries = result;
    });
  }

  public searchCountry($event) {
    console.log(event);
    //this.filtredCountries = this.countries.filter(country => )
  }

  onCountryChange(country) {
    this.nomenclatureService.findCitiesByCountry(country).subscribe(result => {
      this.filteredCities = result;
    });
  }

  public onCityChange(event) {
    this._filter(event.target.value);
  }

  public onCitySet($event) {
    if ($event.target.value.length == 0) delete this.request["city"];
  }

  public _filter(value: string) {
    this.filteredCities = this.cities.filter(city => city.name.toLowerCase().includes(value.toLowerCase()));
  }

  onBoundsChange($event) {
    //console.log(GeoUtils.parseGmapEvent($event));
  }

  onZoomChange($event) {
    this.zoom = $event;
  }

  cityNameFn(event): string {
    return event.name;
  }

  search() {
    this.realEstateService.search(this.request).subscribe(result => this.properties = result);
  }

  realEstateTypeValues() {
    return EnumUtils.getValues(RealEstateType);
  }

}
