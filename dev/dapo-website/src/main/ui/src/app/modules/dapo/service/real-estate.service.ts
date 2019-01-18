import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { PageResponse } from "../../core/rest/page-response";
import { RealEstate } from "../model/v1/real-estate";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RealEstateService {

  private baseUrl:string = "http://localhost:4200/api/v1/realEstate";

  constructor(private http:HttpClient) { }

  public getLatestPropertiesPageable():Observable<PageResponse<RealEstate>> {
    return this.http.get<PageResponse<RealEstate>>(this.baseUrl + "/findAllPageable");
  }

  public getLatestProperties():Observable<Array<RealEstate>> {
    return this.http.get<Array<RealEstate>>(this.baseUrl + "/findAll");
  }
}
