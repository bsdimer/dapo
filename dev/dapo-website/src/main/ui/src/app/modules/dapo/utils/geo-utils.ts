import { GeoBondaries } from "./geo-bondaries";
export class GeoUtils {

  public static parseGmapEvent($event): GeoBondaries {
    var gb = new GeoBondaries();
    gb.topLeft = {x: $event.fa.j, y: $event.ma.j};
    gb.bottomRight = {x: $event.fa.l, y: $event.ma.l};
    return gb;
  }
}
