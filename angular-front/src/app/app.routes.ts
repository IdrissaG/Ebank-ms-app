import {RouterModule, Routes} from '@angular/router';
import {Accounts} from './accounts/accounts';
import {NgModule} from '@angular/core';
import {BotUi} from './bot-ui/bot-ui';


export const routes: Routes = [
  {path : "accounts", component: Accounts},
  {path: "bot", component: BotUi}
];
