package com.example.elmserver.dao;

import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Cart;
import com.example.elmserver.entities.User;

import java.util.List;

public interface CartDao extends AbstractDao<Cart, Integer>{
    List<Cart> findCartsByBusinessAndUser(Business business, User user);

    @Override
    void deleteAllInBatch(Iterable<Cart> entities);
}
