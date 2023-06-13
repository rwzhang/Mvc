package com.example.mvc.repository;

import com.example.mvc.network.MainApi;
import com.example.mvc.network.RetrofitClient;


/**
 * date:2020/4/16 0016
 * time:上午 11:38
 * author:zhaoyang.cheng
 */
public abstract class BaseRepository {


    /**
     * date:2020/4/16 0016
     * time:上午 11:38
     * author:zhaoyang.cheng
     */
    public abstract static class BaseRepository {
        protected MainApi mainApi = RetrofitClient.getInstance("1").getApi();
    }
}
