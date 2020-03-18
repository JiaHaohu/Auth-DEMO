package com.jiahao.Auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

public class AuthToken {

    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1*60*1000;
    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token,long createTime){
        this.token  = token;
        this.createTime = createTime;
    }

    public AuthToken(String token,long createTime,long expiredTimeInterval){
        this.token  = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken create(String urlBase, long createTime, Map<String,String> params){
        String token = null;
        String SPLIT = "&";
        String url = urlBase;
        Set<String> strings = params.keySet();
        for (String key:params.keySet()) {
            url =url+SPLIT+key+"="+params;
        }

        url = url + SPLIT + createTime;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(url.getBytes());
            token = new BigInteger(messageDigest.digest()).toString(32);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new AuthToken(token,System.currentTimeMillis());
    }

    public String getToken(){
        return this.token;
    };

    public boolean isExpired(){
        if (System.currentTimeMillis() - this.createTime>this.expiredTimeInterval){
            return true;
        }
        return false;
    }

    public boolean matchAuthToken(AuthToken authToken){
        if (authToken == null){
            return false;
        }
        if (authToken.getToken().equals(this.getToken()) && (this.createTime==authToken.createTime) ){
            return true;
        }
        return false;
    }
}
