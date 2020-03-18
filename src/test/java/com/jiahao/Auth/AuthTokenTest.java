package com.jiahao.Auth;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AuthTokenTest {

    @Test
    public void create() {
        HashMap<String,String> map = new HashMap<>();
        map.put("AppId","123");
        map.put("password","123456");
        AuthToken authToken = AuthToken.create("hujiahao.org", System.currentTimeMillis(), map);
        System.out.println(authToken.getToken());
    }

    @Test
    public void isExpired() {

    }

    @Test
    public void matchAuthToken() {
    }
}