import { Address } from '../model/address';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';

@Injectable()
export class AddressService {
  client: HttpClient;
  constructor(client: HttpClient) {
    this.client = client;
  }

  baseAddressUrl = "http://localhost:8086/addresses";

  
  addAddress(address: Address): Observable<Address> {
    let url = this.baseAddressUrl + "/add";
    let result: Observable<Address> = this.client.post<Address>(url, address);
    return result;
  }

  findAddressById(id:string): Observable<Address> {
    let url = this.baseAddressUrl + '/get/'+id;
    let observable: Observable<Address> = this.client.get<Address>(url);
    return observable;
  }
  updateAddress(address: Address): Observable<Address> {
    let url = this.baseAddressUrl + "/update";
    let result: Observable<Address> = this.client.put<Address>(url, address);
    return result;
  }

  fetchAllAddresses(): Observable<Address[]> {
    let url = this.baseAddressUrl + "/fetchAll";
    let observable: Observable<Address[]> = this.client.get<Address[]>(url);
    return observable;
  }

  deleteAddress(id:string) {
    let url = this.baseAddressUrl + '/delete/' + id;
    let result: Observable<boolean> = this.client.delete<boolean>(url);
    return result;
  }




}