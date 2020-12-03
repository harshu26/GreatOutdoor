package com.capgemini.go.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.capgemini.go.entities.AddressDto;

import com.capgemini.go.service.IAddressService;
import com.capgemini.go.util.AddressUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
@Validated // required for validating path variables
public class AddressRestController {


	@Autowired
	private IAddressService service;

	@PostMapping("/add")
	public ResponseEntity<Object> createAddress(@RequestBody @Valid Map<String, Object> requestData) {
		AddressDto address = AddressUtil.convertToAddress(requestData);
		Boolean isAdded = service.addAddress(address);
		return new ResponseEntity<>(isAdded, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<AddressDto> getAddress(@PathVariable("id") String addressId) {
		AddressDto address = service.findById(addressId);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Object> updateAddress(@RequestBody @Valid Map<String, Object> requestData) {
		AddressDto address = AddressUtil.convertToAddress(requestData);
		boolean isUpdated = service.updateAddress(address);
		return new ResponseEntity<>(isUpdated, HttpStatus.OK);
	}
	@GetMapping("/fetchAll")
	public ResponseEntity<List<AddressDto>> fetchAllAddress() {
		List<AddressDto> addresses = service.viewAllAddress();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteAddress( @PathVariable("id") String addressId) {
		//String id=(String)requestData.get("addressId");
		AddressDto address = service.findById(addressId);
		Boolean isRemoved = service.deleteAddress(address);
		return new ResponseEntity<>(isRemoved, HttpStatus.OK);
	}

	
}
