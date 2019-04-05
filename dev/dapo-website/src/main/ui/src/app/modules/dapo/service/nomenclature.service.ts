import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { City } from "../model/v1/city";
import {Country} from "../model/v1/country";
import { District } from "../model/v1/District";
import { Municipality } from "../model/v1/Municipality";

@Injectable({
  providedIn: 'root'
})
export class NomenclatureService {

  private _cities: City[];
  private _municipalities: Municipality[];
  private _districts: District[];
  private _countries: Country[];

  constructor(public http: HttpClient) {
    this.getCountries().subscribe(result => this._countries = result);
    this.getDistricts().subscribe(result => this._districts = result);
  }

  public getCities(): Observable<City[]> {
    return this.http.get<City[]>(environment.apiPrefix + "/nm/city");
  }

  public getDistricts(): Observable<District[]> {
    return this.http.get<District[]>(environment.apiPrefix + "/nm/district");
  }

  public getMunicipalities(): Observable<Municipality[]> {
    return this.http.get<Municipality[]>(environment.apiPrefix + "/nm/municipality");
  }

  public findCitiesByCountry(country: Country): Observable<City[]> {
    return this.http.get<City[]>(environment.apiPrefix + "/nm/city/findAllByCountry?country=" + country.id);
  }

  public findDistrictsByCountry(country: Country): Observable<District[]> {
    return this.http.get<District[]>(environment.apiPrefix + "/nm/district/findAllByCountry?country=" + country.id);
  }

  public getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(environment.apiPrefix + "/nm/country");
  }

  public getPropertyTypes(): Observable<Array<string>> {
    return this.http.get<Array<string>>(environment.apiPrefix + "/nm/pt");
  }

  get districts(): Observable<District[]> {
    if (this._districts && this._districts.length > 0) return of(this._districts);
    return this.getDistricts();
  }

  get municipalities(): Observable<Municipality[]> {
    if (this._municipalities && this._municipalities.length > 0) return of(this._municipalities);
    return this.getMunicipalities();
  }

  get cities(): Observable<City[]> {
    if (this._cities && this._cities.length > 0) return of(this._cities);
    return this.getCities();
  }

  get countries(): Observable<Country[]> {
    if (this._countries && this._countries.length > 0) return of(this._countries);
    return this.getCountries();
  }


}
