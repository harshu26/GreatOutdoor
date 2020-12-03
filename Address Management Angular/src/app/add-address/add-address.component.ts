import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { AddressService} from '../services/addressservice';


@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit {

  service: AddressService
  constructor(service: AddressService) {
    this.service = service;
  }

  ngOnInit(): void {
  }
  addedAddress: Address = null;

  addAddress(formData: any) {
    let data = formData.value;
    //let addressId = data.addressId;
    let retailerId = data.retailerId;
    let buildingNo = data.buildingNo;
    let city = data.city;
    let zip = data.zip;
    let field = data.field
    let state = data.state;

    this.addedAddress = new Address();
    
    this.addedAddress.retailerId = retailerId;
    this.addedAddress.buildingNo = buildingNo;
    this.addedAddress.city = city;
    this.addedAddress.state = state;
    this.addedAddress.zip = zip;
    this.addedAddress.field = field;

    let result = this.service.addAddress(this.addedAddress); 
    result.subscribe((address: Address) => {
      this.addedAddress = address;
    },
      err => {
        console.log("err=" + err);
      });
    formData.reset();

  }
}

