import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { City } from "../model/v1/city";

@Injectable({
  providedIn: 'root'
})
export class NomenclatureService {

  public _cities: City[];

  constructor(public http: HttpClient) {
    this.getCities().subscribe(result => this._cities = result);
  }

  public getCities(): Observable<City[]> {
    return this.http.get<City[]>(environment.apiPrefix + "/nm/cities");
  }

  public getPropertyTypes(): Observable<Array<string>> {
    return this.http.get<Array<string>>(environment.apiPrefix + "/nm/pt");
  }

  get cities(): Observable<City[]> {
    if (this._cities && this._cities.length > 0) return of(this._cities);
    return this.getCities();
  }


}
