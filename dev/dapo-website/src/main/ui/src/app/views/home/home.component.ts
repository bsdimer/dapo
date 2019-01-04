import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "../../modules/core";
import { ConfigEvent } from "../../modules/core";
import { RealEstateService } from "../../modules/dapo/service/real-estate.service";
import { PageResponse } from "../../modules/core/rest/page-response";
import { RealEstate } from "../../modules/dapo/model/real-estate";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private config:any;
  private latestProperties: PageResponse<RealEstate> = new PageResponse();

  constructor(private broadcaster: Broadcaster, private realEstateService: RealEstateService) {
  }

  ngOnInit() {
    this.broadcaster.$on(ConfigEvent.LOADED_SUCCESSFULLY).subscribe((result) => {
      this.config = result.data;

      this.realEstateService.getLatestProperties().subscribe(
        result => {
          this.latestProperties = result;
          console.log(result);
        }
      );
    });
  }

}
