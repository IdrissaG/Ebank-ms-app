import { Component, inject } from '@angular/core';
import {RouterModule} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AsyncPipe} from '@angular/common';
import {AccountList, AccountModel, RequestStatus} from '../model/account.model';
import {catchError, map, Observable, of} from 'rxjs';
import {Loading} from '../services/loading';


@Component({
  selector: 'app-accounts',
  imports: [RouterModule, AsyncPipe],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts {
  private http= inject(HttpClient);
  loadingService= inject(Loading);
  accounts$: Observable<AccountList> = this.http.get<AccountModel[]>
  ('http://localhost:9999/EBANK-SERVICE/accounts').pipe(
    map(response =>{
      return {accounts: response, status:RequestStatus.SUCCESS}
      }),
    catchError((error, caught) => {
      return of({status: RequestStatus.ERROR, errorMessage: error.statusText});
    })
  ); //observable

  protected readonly RequestStatus = RequestStatus;
}
