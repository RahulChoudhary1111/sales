package com.sales.services;


import com.sales.dto.ItemDto;
import com.sales.entities.Item;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends RepoContainer {




    public Page<Item> getAllItems() {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 1, sort);
        return itemRepository.findAll(pageable);
    }

    public Item getItemBySlug(String slug) {
        return itemRepository.findItemBySlug(slug);
    }

    public Item addItem(ItemDto itemDto, User loggedUser){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(item.getPrice());
        item.setDiscount(item.getDiscount());
        item.setRating(item.getRating());
        item.setDescription(item.getDescription());
        item.setInStock(item.getInStock());
        item.setUpdatedAt(Utils.getCurrentMillis());
        item.setCreatedAt(Utils.getCurrentMillis());
//        item.setCreatedBy(loggedUser.getId());
        item.setUpdatedBy(loggedUser.getId());
        return itemRepository.save(item);
    }






}
