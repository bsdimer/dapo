import { Component, OnInit } from '@angular/core';
import { Broadcaster } from "./modules/core/events/broadcaster";
import { timeout } from "rxjs/internal/operators";
import { ConfigurationService } from "./modules/core/configuration/configuration.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {


  constructor(private configService: ConfigurationService) {
  }

  ngOnInit(): void {
    this.configService.getConfig();
  }

}
