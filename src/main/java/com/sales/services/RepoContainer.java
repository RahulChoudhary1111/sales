package com.sales.services;


import com.sales.dto.SearchFilters;
import com.sales.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class RepoContainer {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHbRepository userHbRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StoreHbRepository storeHbRepository;


    public Pageable getPageable(SearchFilters filters){
        Sort sort = (filters.getOrder().equalsIgnoreCase("asc")) ?
                Sort.by(filters.getOrderBy()).ascending() :  Sort.by(filters.getOrderBy()).descending();
        Pageable pageable = PageRequest.of(filters.getPageNumber(), filters.getSize(),sort);
        return pageable;
    }



}
