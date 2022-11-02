package com.example.elmserver;

import com.example.elmserver.dao.BusinessDao;
import com.example.elmserver.dao.DeliveryaddressDao;
import com.example.elmserver.dao.FoodDao;
import com.example.elmserver.dao.UserDao;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.entities.Food;
import com.example.elmserver.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ElmServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    BusinessDao businessdao;
    @Resource
    FoodDao fooddao;
    @Resource
    UserDao userdao;
    @Resource
    DeliveryaddressDao ddao;
    @Test
    void BusinessDaoTest() {
//        Collection<Integer> collection=new ArrayList<Integer>();
//        collection.add(1);
//        collection.add(9);
//        List<Business> list= businessdao.findByPrimaryKeyIn(collection);
//        List<Business> list= businessdao.findByPrimaryKeyNotIn(collection);
//        Collection<Business> collection=new ArrayList<Business>();
//        collection.add(Business.builder().name("testbusiness3").password("777").id(8)
//                .address("wfjwofj").build());
//        Map<Integer, Business> map=businessdao.findMapByPrimaryKeyIn(collection);
//        List<Food> list= fooddao.findAllByBusiness(Business.builder().name("testbusiness3").password("777").id(1).address("wfjwofj").build());
        List<Deliveryaddress> list=ddao.findAllByUser(User.builder().id("1").name("OOO").password("SSS").build());
        System.out.println(list.get(0));
    }



}
