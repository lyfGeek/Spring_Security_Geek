package com.geek.security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void testBCrypt() {
        // 对密码进行加密。
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());

        // hashpw = $2a$10$ZV2gne5gM44.eRTi0KFOK.MJ/OmjB3h6Aw1sUk9YoOU8rbSUHtYwG
        // hashpw = $2a$10$CKaHgxPgt0WU3SWihmSR6uOzTURqJqH8jkZ/xLGikfm2KBj1E76Jy

        System.out.println("～　～　～　～　～　～　～");
        System.out.println("hashpw = " + hashpw);
        System.out.println("～　～　～　～　～　～　～");

        // 校验密码。
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$ZV2gne5gM44.eRTi0KFOK.MJ/OmjB3h6Aw1sUk9YoOU8rbSUHtYwG");
        System.out.println("checkpw = " + checkpw);
        boolean checkpw1 = BCrypt.checkpw("123", "$2a$10$CKaHgxPgt0WU3SWihmSR6uOzTURqJqH8jkZ/xLGikfm2KBj1E76Jy");
        System.out.println("checkpw1 = " + checkpw1);
    }
}
