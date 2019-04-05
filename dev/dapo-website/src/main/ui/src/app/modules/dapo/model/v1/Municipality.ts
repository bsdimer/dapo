import { City } from "./city";
export class Municipality {
  id: number;
  name: string;
  code: string;
  cities: City[] = [];
}
