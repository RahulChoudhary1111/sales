package com.sales.admin.services;

import com.sales.dto.AddressDto;
import com.sales.dto.SearchFilters;
import com.sales.dto.StoreDto;
import com.sales.entities.Address;
import com.sales.entities.Store;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StoreService extends RepoContainer{


    @Autowired
    AddressService addressService;

    public Page<Store> getAllStore(SearchFilters searchFilters) {
        Pageable pageable = getPageable(searchFilters);
        return storeRepository.findAll(pageable);
    }



    public AddressDto getAddressObjFromStore(StoreDto storeDto){
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(storeDto.getCity());
        addressDto.setState(storeDto.getState());
        addressDto.setLatitude(storeDto.getLatitude());
        addressDto.setAltitude(storeDto.getAltitude());
        return addressDto;
    }


    @Transactional
    public Map<String, Object> createOrUpdateStore(StoreDto storeDto,User loggedUser) {
        Map<String, Object> responseObj = new HashMap<>();
        if (Utils.isEmpty(storeDto.getSlug())) {
            int isUpdated = updateStore(storeDto, loggedUser);
            if (isUpdated > 0) {
                responseObj.put("message", "successfully updated.");
                responseObj.put("status", 201);
            } else {
                responseObj.put("message", "nothing to updated. may be something went wrong");
                responseObj.put("status", 400);
            }
            return responseObj;
        } else {
            Store createdStore = createStore(storeDto, loggedUser);
            if (createdStore.getId() > 0) {
                responseObj.put("res", createdStore);
                responseObj.put("message", "successfully inserted.");
                responseObj.put("status", 200);
            } else {
                responseObj.put("message", "nothing to insert. may be something went wrong");
                responseObj.put("status", 400);
            }
        }
        return responseObj;
    }

    @Transactional
    public Store createStore(StoreDto storeDto , User loggedUser){


        /** inserting  address during create a wholesale */
        AddressDto addressDto = getAddressObjFromStore(storeDto);
        Address address =  addressService.insertAddress(addressDto,loggedUser);
        /** @END inserting  address during create a wholesale */

        Store store = new Store();
        if(storeDto.getUserId()==null || storeDto.getUserId()<1 ) return store;
        Optional<User> storeOwner = userRepository.findById(storeDto.getUserId());
        if (storeOwner == null) return store;

        store = new Store(loggedUser);
        store.setStoreName(storeDto.getStoreName());
        store.setEmail(storeDto.getEmail());
        store.setAddress(address);
        store.setDescription(storeDto.getDescription());
        store.setPhone(storeDto.getPhone());
        store.setRating(storeDto.getRating());
        return storeRepository.save(store);
    }

    public int updateStore(StoreDto storeDto, User loggedUser){
        return storeHbRepository.updateStore(storeDto,loggedUser);
    }

    public int deleteStoreBySlug(String slug){
        return storeHbRepository.deleteStore(slug);
    }

    @Transactional
    public Store getStoreDetails(String slug){
        return storeRepository.findStoreBySlug(slug);
    }

}
