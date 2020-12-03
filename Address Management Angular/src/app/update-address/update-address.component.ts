import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { AddressService} from '../services/addressservice';

@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.css']
})
export class UpdateAddressComponent implements OnInit {

  service: AddressService;
  constructor(service: AddressService) {
    this.service = service;
  }
  
  updatedAddress=null;
  errMsg=null;
  ngOnInit(): void {
  }
  updateAddress(updateForm:any)
  {
    let data = updateForm.value;

    let addressId = data.addressId;
    let retailerId = data.retailerId;
    let buildingNo = data.buildingNo;
    let city = data.city;
    let zip = data.zip;
    let field = data.field
    let state = data.state;
    this.updatedAddress = new Address();
    this.updatedAddress.addressId =addressId;
    this.updatedAddress.retailerId = retailerId;
    this.updatedAddress.buildingNo = buildingNo;
    this.updatedAddress.city = city;
    this.updatedAddress.state = state;
    this.updatedAddress.zip = zip;
    this.updatedAddress.field = field;

    let result = this.service.updateAddress(this.updatedAddress); 
    result.subscribe((address: Address) => {
      this.updatedAddress = address;
    },
      err => {
        this.errMsg=err.error;
        console.log("err=" + err);
      });
    updateForm.reset();
  }
}
