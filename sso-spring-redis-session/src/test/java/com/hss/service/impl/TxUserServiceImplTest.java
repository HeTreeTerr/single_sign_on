package com.hss.service.impl;

import com.hss.service.TxUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TxUserServiceImplTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TxUserService txUserService;

    @Test
    public void updateUserSex() {
        //txUserService.updateUserSex(1L,1,true);

        txUserService.updateUserSex(1L,0,false);
    }

}