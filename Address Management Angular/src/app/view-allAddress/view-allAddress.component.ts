import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { AddressService } from '../services/addressservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-allAddress',
  templateUrl: './view-allAddress.component.html',
  styleUrls: ['./view-allAddress.component.css']
})
export class ViewAllAddressComponent implements OnInit {

  allAddress: Address[] = [];
  service: AddressService;

  
  constructor(service: AddressService) {
    this.service = service;
    let observable: Observable<Address[]> = this.service.fetchAllAddresses();
    observable.subscribe(add => {
      this.allAddress = add;
      console.log("inside success callback =" + this.allAddress.length);
    }, err => console.log(err));
  }

//  private getAllAddress() {
 //   
  //}

  ngOnInit(): void {
  }

  removeAddress(addressId: string) {
    let result: Observable<boolean> = this.service.deleteAddress(addressId);
    result.subscribe(add => {
      this.removeLocalAddress(addressId);
     // this.getAllAddress();
    }, err => {
      console.log("err in deleteing record=" + err);
    })
  }

  removeLocalAddress(addressId:string)
{
let foundIndex=-1;
for(let i=0;i<this.allAddress.length;i++){
 let address=this.allAddress[i];
 if(address.addressId===addressId){
   foundIndex=i;
   break;
 }
}
if(foundIndex<0){
 return;
}
this.allAddress.splice(foundIndex,1);
}

}
  




