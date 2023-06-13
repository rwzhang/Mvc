package com.example.mvc.network;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvc.MainActivity;
import com.example.mvc.bean.ApiRespond;

/**
 * date:2020/4/16 0016
 * time:上午 11:38
 * author:zhaoyang.cheng
 */
public abstract class BaseRepository {
    protected MainApi mainApi = RetrofitClient.getInstance("1").getApi();
}
