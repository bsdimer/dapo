import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "../../modules/core";
import { ConfigEvent } from "../../modules/core";
import { RealEstateService } from "../../modules/dapo/service/real-estate.service";
import { PageResponse } from "../../modules/core/rest/page-response";
import { RealEstate } from "../../modules/dapo/model/v1/real-estate";
import { google } from "@agm/core/services/google-maps-types";
import { GeoUtils } from "../../modules/dapo/utils/geo-utils";
import { ConfigurationService } from "../../modules/core/configuration/configuration.service";
import { AuthenticationService } from "../../modules/core/authentication/authentication.service";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private config: any;
  private latestProperties: any;

  private zoom: number = 8;

  constructor(private broadcaster: Broadcaster,
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

    /*var service = new google.maps.places.PlacesService(map);
     service.nearbySearch({
     location : myPlace,
     radius : 5500,
     type : [ 'restaurant' ]
     }, callback);*/
  }


  onBoundsChange($event) {
    //console.log(GeoUtils.parseGmapEvent($event));
  }

  onZoomChange($event) {
    this.zoom = $event;
  }

  test() {
    console.log(this.authenticationService.isAuthenticated);
    console.log(this.authenticationService.currentUser);
  }
}
