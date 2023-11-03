package com.sales.services;


import com.sales.dto.ItemDto;
import com.sales.dto.UserDto;
import com.sales.entities.Item;
import com.sales.entities.User;
import com.sales.repositories.ItemRepository;
import com.sales.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class itemService extends RepoContainer {




    public Page<Item> getAllItems() {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(1, 1, sort);
      //return userRepository.findAll(pageable);
        return itemRepository.findAll(pageable);

    }

    public Item addItem(ItemDto itemDto, User loggedUser){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setWholesale_id(item.getWholesale_id());
        item.setPrice(item.getPrice());
        item.setDiscount(item.getDiscount());
        item.setRating(item.getRating());
        item.setDescription(item.getDescription());
        item.setInstock(item.getInstock() );

        item.setUpdatedAt(Utils.getCurrentMillis());
        item.setCreatedAt(Utils.getCurrentMillis());
//        item.setCreatedBy(loggedUser.getId());
        item.setUpdatedBy(loggedUser.getId());
        return itemRepository.save(item);
    }






}
