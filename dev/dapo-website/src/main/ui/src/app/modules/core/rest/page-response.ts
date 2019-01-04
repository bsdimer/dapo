import { PageableResponse } from "./pageable-response";
import { SortResponse } from "./sort-response";
export class PageResponse<T> {
  content: Array<T>;
  pageable: PageableResponse;
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;
  sort: SortResponse;
  numberOfElements: number;
  size: number;
  number: number;
}
