export interface AccountModel{
  id?:string;
  balance?:number;
  Type?:string;
  customerId?:number;
  createAt?:string;
}

export enum RequestStatus{
  SUCCESS, ERROR
}

export interface AccountList{
  accounts?:AccountModel[];
  status?: RequestStatus;
  errorMessage?: string;
}
