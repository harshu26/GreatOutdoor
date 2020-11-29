package com.capgemini.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.go.entities.AddressDto;

@Repository
public interface IAddressDao extends JpaRepository<AddressDto, String> {

}
