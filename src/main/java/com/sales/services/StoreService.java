package com.sales.services;

import com.sales.dto.PaginationDto;
import com.sales.dto.StoreDto;
import com.sales.entities.Store;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StoreService extends RepoContainer{


    public Page<Store> getAllStore(PaginationDto paginationDto) {
        Sort sort = Sort.by(paginationDto.getAsc()).ascending();
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), sort);
        return storeRepository.findAll(pageable);

    }

    public Map<String, Object> createOrUpdateStore(StoreDto storeDto, User loggedUser) {
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

    public Store createStore(StoreDto storeDto, User loggedUser){

        if(storeDto.getUserId()==null || storeDto.getUserId()<1 ) return new Store();

        Store store = new Store();
        store.setStoreName(storeDto.getStoreName());
        store.setEmail(storeDto.getEmail());
        store.setAddress(storeDto.getAddress());
        store.setLatitude(storeDto.getLatitude());
        store.setLongitude(storeDto.getLongitude());
        store.setDescription(storeDto.getDescription());
        store.setIsDeleted("N");
        store.setPhone(storeDto.getPhone());
        store.setRating(storeDto.getRating());
        store.setUpdatedBy(loggedUser.getId());
        store.setUpdatedAt(Utils.getCurrentMillis());
        store.setCreatedAt(Utils.getCurrentMillis());
        store.setCreatedBy(loggedUser.getId());
        store.setSlug(UUID.randomUUID().toString());
        store.setStatus("A");


        User storeOwner = new User();
        storeOwner.setId(storeDto.getUserId());
        store.setUser(storeOwner);

        return storeRepository.save(store);
    }

    public int updateStore(StoreDto storeDto, User loggedUser){
        return storeHbRepository.updateStore(storeDto,loggedUser);
    }

    public int deleteStoreBySlug(String slug){
        return storeHbRepository.deleteStore(slug);
    }

    public Store getStoreDetails(String slug){
        return storeRepository.findStoreBySlug(slug);
    }

}
