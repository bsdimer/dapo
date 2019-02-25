import { SortResponse } from "./sort-response";
export class PageableResponse
{
  sort: SortResponse;
  pageSize: number;
  pageNumber: number;
  offset: number;
  paged: boolean;
}
