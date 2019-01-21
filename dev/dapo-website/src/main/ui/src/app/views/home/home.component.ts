import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "../../modules/core";
import { ConfigEvent } from "../../modules/core";
import { RealEstateService } from "../../modules/dapo/service/real-estate.service";
import { PageResponse } from "../../modules/core/rest/page-response";
import { RealEstate } from "../../modules/dapo/model/v1/real-estate";
import { google } from "@agm/core/services/google-maps-types";
import { GeoUtils } from "../../modules/dapo/utils/geo-utils";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private config: any;
  private latestProperties: any;

  private zoom: number = 8;

  constructor(private broadcaster: Broadcaster, private realEstateService: RealEstateService) {
  }

  ngOnInit() {
    this.broadcaster.$on(ConfigEvent.CONFIG_LOADED_SUCCESSFULLY).subscribe((result) => {
      this.config = result.data;
      this.realEstateService.getLatestProperties().subscribe(
        result => this.latestProperties = result
      )
    });

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

}
