package com.jiahao.Auth;

import java.util.HashMap;

public class DefaultApiAuthencatorImpl implements ApiAuthencator {

    private CredentialStorage credentialStorage;

    public DefaultApiAuthencatorImpl(){}

    public DefaultApiAuthencatorImpl(CredentialStorage credentialStorage){
        this.credentialStorage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        long timestamp = apiRequest.getTimestamp();
        String token = apiRequest.getToken();
        String baseUrl = apiRequest.getBaseUrl();
        AuthToken clientAuthToken = new AuthToken(token,timestamp);
        if (clientAuthToken.isExpired()){
            throw new RuntimeException("Token is expired!");
        }

        String password = credentialStorage.getPasswordByAppId(appId);
        HashMap<String,String> map = new HashMap();
        map.put("appId",appId);
        map.put("password",password);

        AuthToken serverAuthToken = AuthToken.create(baseUrl, timestamp, map);

        if (!serverAuthToken.matchAuthToken(clientAuthToken)){
            throw new RuntimeException("Token verfication failed");
        }


    }
}
