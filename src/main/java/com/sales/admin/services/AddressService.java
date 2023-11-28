package com.sales.admin.services;

import com.sales.dto.AddressDto;
import com.sales.entities.Address;
import com.sales.entities.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.sales.utils.Utils.getCurrentMillis;

@Service
public class AddressService extends RepoContainer {

    public Address insertAddress(AddressDto addressDto, User loggedUser){
        Address address = new Address();
        address.setSlug(UUID.randomUUID().toString());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setLatitude(addressDto.getLatitude());
        address.setAltitude(addressDto.getAltitude());
        address.setCreatedAt(getCurrentMillis());
        address.setCreatedBy(loggedUser.getId());
        address.setUpdatedAt(getCurrentMillis());
        address.setUpdatedBy(loggedUser.getId());
        return  addressRepository.save(address);
    }

    public Address findAddressBySlug(String slug){
        return addressRepository.findAddressBySlug(slug);
    }
    public int  updateAddress(AddressDto addressDto,User loggedUser){
        return addressHbRepository.updateAddress(addressDto,loggedUser);
    }

}
