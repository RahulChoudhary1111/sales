package com.sales.services;

import com.sales.dto.PaginationDto;
import com.sales.dto.StoreDto;
import com.sales.entities.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class StoreService extends RepoContainer{


    public Page<Store> getAllStore(PaginationDto paginationDto) {
        Pageable pageable = getPageable(paginationDto);
        return storeRepository.findAll(pageable);
    }

    public Store createStore(StoreDto storeDto,Store loggedStore){
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
        store.setSlug(UUID.randomUUID().toString());
        store.setStatus("A");
        return storeRepository.save(store);
    }

//    public int updateStore(StoreDto storeDto, Store loggedStore){
//        return storeHbRepository.updateStore(storeDto,loggedStore);
//    }
}
