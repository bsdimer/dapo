import { filter } from 'rxjs/operators';
import { BroadcastEvent } from "./broadcast-event";
import { Subject, Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class Broadcaster {
  private _event: Subject<BroadcastEvent>;

  constructor() {
    /**
     * Register event as a RxJS subject
     * @type {Subject<BroadcastEvent>}
     * @private
     */
    this._event = new Subject<BroadcastEvent>();
  }

  /**
   * Broadcast app wide event
   * MUST contain a string key
   * MAY contain data to be broadcast with the event
   * MAY contain a broadcaster, which is the component broadcasting the event
   * @param key: any (mandatory)
   * @param data: any (optional)
   * @param target: any (optional)
   */
  $broadcast(key: any, data?: any, target?: any) {
    this._event.next({key, data});
  }

  /**
   * Register event and return an observable to subscribe to
   * @param key: string
   * @returns {Observable<T>}
   */
  $on<T>(key: any): Observable<BroadcastEvent> {
    return this._event.asObservable().pipe(filter((event: BroadcastEvent) => event.key === key));

  }
}
