import {RouterModule, Routes} from '@angular/router';
import {Accounts} from './accounts/accounts';
import {NgModule} from '@angular/core';


export const routes: Routes = [
  {path : "accounts", component: Accounts}
];
