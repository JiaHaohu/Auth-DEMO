package com.jiahao.Auth;

public class MysqlCredentialStorage implements CredentialStorage{
    @Override
    public String getPasswordByAppId(String appId) {
        if (appId.equals("123")){
            return "123456";
        }
        return null;
    }
}
