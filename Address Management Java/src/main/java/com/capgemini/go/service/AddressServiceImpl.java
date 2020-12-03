package com.capgemini.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.go.dao.IAddressDao;
import com.capgemini.go.entities.AddressDto;
import com.capgemini.go.exceptions.AddressAlreadyExistsException;
import com.capgemini.go.exceptions.AddressNotFoundException;
import com.capgemini.go.exceptions.IncorrectArgumentException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressDao addressDao;

	public String generatedId() {
		StringBuilder id = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
			int number = random.nextInt(9);
			id.append(number);
		}
		return id.toString();
	}

	@Override
	public boolean addAddress(AddressDto addressDTO) {
		String id = generatedId();
		if(id.equals(addressDTO.getAddressId())){
			throw new AddressAlreadyExistsException("Address Already Present!");
		}
		addressDTO.setAddressId(id);
		addressDao.save(addressDTO);
		return true;
	}

	@Override
	public boolean deleteAddress(AddressDto addressDTO) {
		addressDao.delete(addressDTO);
		return true;
	}

	@Override
	public boolean updateAddress(AddressDto addressDTO) {
		boolean exists = addressDao.existsById(addressDTO.getAddressId());
		if (exists) {
			addressDao.save(addressDTO);
			return true;
		}
		throw new AddressNotFoundException("Address not found for id=" + addressDTO.getAddressId());
	}

	@Override
	public List<AddressDto> viewAllAddress() {
		return addressDao.findAll();
	}

	@Override
	public AddressDto findById(String addressId) {
		if (addressId.isEmpty()) {
			throw new IncorrectArgumentException("Address Id is not correct!");
		}
		Optional<AddressDto> optional = addressDao.findById(addressId);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new AddressNotFoundException("Address not found for id=" + addressId);
	}

}
