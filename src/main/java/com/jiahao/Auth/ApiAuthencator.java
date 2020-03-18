package com.jiahao.Auth;

public interface ApiAuthencator {
    void auth(String utl);
    void auth(ApiRequest apiRequest);
}
