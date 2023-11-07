package com.sales.services;


import com.sales.dto.PaginationDto;
import com.sales.repositories.ItemRepository;
import com.sales.repositories.StoreRepository;
import com.sales.repositories.UserHbRepository;
import com.sales.repositories.UserRepository;
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


    public Pageable getPageable(PaginationDto paginationDto){
        Sort sort = (paginationDto.getOrder().equalsIgnoreCase("asc")) ?
                Sort.by(paginationDto.getSortBy()).ascending() :  Sort.by(paginationDto.getSortBy()).descending();
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(),sort);
        return pageable;
    }



}
