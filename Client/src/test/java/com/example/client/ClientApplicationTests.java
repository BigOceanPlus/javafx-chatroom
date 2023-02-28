package com.example.client;

import com.example.client.common.FileUtil;
import com.example.client.common.SpringContextUtil;
import com.example.client.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

    @Test
    void contextLoads() {
        //System.out.println(SpringContextUtil.getBean(UserService.class).findAll());
        SpringContextUtil.getBean(FileUtil.class).WriteFile("login.dat", new String[]{"user", "false", "123456"});
        SpringContextUtil.getBean(FileUtil.class).PasswordSavedVerification("login.dat");
    }

}
