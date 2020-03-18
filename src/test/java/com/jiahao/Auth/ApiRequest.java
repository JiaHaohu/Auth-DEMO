package com.jiahao.Auth;

public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl,
                      String token,
                      String appId,long timestamp){
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public static ApiRequest createFromFullUrl(String url){
        if (url!=null){
            return null;
        }
        String[] strArr = url.split("&");
        if (strArr.length!=4){
            return null;
        }
        return new ApiRequest(strArr[0],strArr[1],strArr[2],Long.parseLong(strArr[3]));
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public String getToken(){
        return this.getToken();
    }

    public long getTimestamp(){
        return this.timestamp;
    }
}
