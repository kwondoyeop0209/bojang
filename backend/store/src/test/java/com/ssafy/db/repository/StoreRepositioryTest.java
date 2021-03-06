package com.ssafy.db.repository;

import com.ssafy.db.entity.Market;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.StoreType;
import com.ssafy.db.entity.User;
import com.ssafy.db.mapping.store.StoreMapping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoreRepositioryTest {

    @Autowired
    StoreRepositiory storeRepositiory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreTypeRepository storeTypeRepository;

    @Autowired
    MarketRepository marketRepository;

    @Test
    public void createStore() {
        Store store = new Store();

        store.setName("야채과일");
        store.setSection(8);
        store.setComment("싱싱한 야채와 과일 팔아요~");
        store.setAddress("싸피시 싸피동 321-321");
        Market market = marketRepository.findById(1L).get();
        StoreType storeType = storeTypeRepository.findById(1L).get();
        store.setMarket(market);
        store.setStoreType(storeType);

        storeRepositiory.save(store);

        System.out.println(store.getId());


    }

    @Test
    public void findcreateStoreByUserId() {

        User user = userRepository.findById("S99b735248a08").get();
        Store store = storeRepositiory.findByUser(user).get();

        Store store1 = storeRepositiory.findByUser_Id("S99b735248a08").get();

        System.out.println(store.getId());

        System.out.println(store1.getId());

    }

    @Test
    public void findByName() {
        List<StoreMapping> list = storeRepositiory.findByNameContaining("가게");
        for (StoreMapping store : list) {
            System.out.println(store.getId());
            System.out.println(store.getName());
        }
    }


}