import { Municipality } from "./Municipality";
export class District {
  id: number;
  name: string;
  code: string;
  municipalities: Municipality[] = [];
}
