package com.capgemini.go.util;

import java.util.Map;

import com.capgemini.go.entities.AddressDto;

public class AddressUtil {
	private AddressUtil() {

	}

	public static AddressDto convertToAddress(Map<String, Object> map) {
		AddressDto address = new AddressDto();
		String id = (String) map.get("addressId");
		String buildingNo = (String) map.get("buildingNo");
		String city = (String) map.get("city");
		String field = (String) map.get("field");
		String retailerId = (String) map.get("retailerId");
		String state = (String) map.get("state");
		String zip = (String) map.get("zip");
		address.setAddressId(id);
		address.setBuildingNo(buildingNo);
		address.setCity(city);
		address.setField(field);
		address.setRetailerId(retailerId);
		address.setState(state);
		address.setZip(zip);
		return address;
	}

}
