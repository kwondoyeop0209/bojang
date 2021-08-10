package com.ssafy.common.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestUtilTest {

    @Autowired
    RestUtil restUtil;

    @Test
    public void billing() {
        String orderInfoId = "1";
        System.out.println(restUtil.postBilling(orderInfoId));
    }
}