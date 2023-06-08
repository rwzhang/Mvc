package com.example.mvc.network;

import androidx.lifecycle.LiveData;

import com.example.mvc.bean.BaseResponse;
import com.example.mvc.bean.UrlsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * API接口
 *
 * @author zhangrenwei
 */
public interface MainApi {

    @GET("://api.github.com/")
    LiveData<BaseResponse<UrlsBean>> getGithubHttp();

    @GET("://api.github.com/")
    Observable<BaseResponse<UrlsBean>> getGithubHttps();

}
