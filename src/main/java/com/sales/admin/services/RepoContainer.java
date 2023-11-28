package com.sales.admin.services;


import com.sales.admin.repositories.*;
import com.sales.dto.SearchFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class RepoContainer {

    @Autowired
    protected StoreRepository storeRepository;
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserHbRepository userHbRepository;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected ItemHbRepository itemHbRepository;
    @Autowired
    protected StoreHbRepository storeHbRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected  AddressHbRepository addressHbRepository;


    public Pageable getPageable(SearchFilters filters){
        Sort sort = (filters.getOrder().equalsIgnoreCase("asc")) ?
                Sort.by(filters.getOrderBy()).ascending() :  Sort.by(filters.getOrderBy()).descending();
        Pageable pageable = PageRequest.of(filters.getPageNumber(), filters.getSize(),sort);
        return pageable;
    }



}
