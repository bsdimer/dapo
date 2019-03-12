import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RealEstate} from "../model/v1/real-estate";
import {Observable, of} from "rxjs";
import {PageResponse} from "../../core/rest/page-response";
import {HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RealEstateService {

  public apiPrefix: string = environment.apiPrefix;
  public entityPrefix: string = "/realEstate";

  constructor(public http: HttpClient) {
  }

  public search(parameters?): Observable<RealEstate[]> {
    return this.http.get<RealEstate[]>(this.apiPrefix + this.entityPrefix + "/search", {params: parameters});
  }

  public searchPageable(parameters?): Observable<PageResponse<RealEstate>> {
    return this.http.get<PageResponse<RealEstate>>(this.apiPrefix + this.entityPrefix + "/search-pageable", {params: parameters});
  }

}
