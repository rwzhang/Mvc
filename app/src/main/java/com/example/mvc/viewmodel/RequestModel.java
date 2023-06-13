package com.example.mvc.viewmodel;

import androidx.lifecycle.LifecycleOwner;

import com.example.mvc.bean.BaseResponse;
import com.example.mvc.bean.GetBean;
import com.example.mvc.bean.PostBean;
import com.example.mvc.network.BaseObserver;
import com.example.mvc.network.ResultListener;
import com.example.mvc.network.RetrofitClient;
import com.example.mvc.network.RxHelper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestModel {
    /**
     * Retrofit+okhttp+Rxjava
     * get请求
     * @param life
     * @param resultListener
     */
    public void getHttp(LifecycleOwner life, ResultListener<List<GetBean>> resultListener) {
        RetrofitClient
                .getInstance("0")
                .getApi()
                .getHttp("666666", 190000)
                .compose(RxHelper.observableIO2Main(life))
                .subscribe(new BaseObserver<List<GetBean>>() {

                    @Override
                    public void onSuccess(List<GetBean> result) {
                        if (result != null) {
                            resultListener.onResult(result);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        resultListener.onError(errorMsg);
                    }
                });

    }
    public void postJsons(LifecycleOwner owner, String keyword, ResultListener<PostBean> resultListener){
        RetrofitClient.getInstance("0")
                .getApi()
                .postJsons("6666666",keyword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PostBean>(){

                    @Override
                    public void onSuccess(PostBean result) {
                        resultListener.onResult(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }
    public void postJsonser(LifecycleOwner owner, String keyword, ResultListener<PostBean> resultListener){
        RetrofitClient.getInstance("0")
                .getApi()
                .postJsonser("6666666",keyword)
                .enqueue(new Callback<BaseResponse<PostBean>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<PostBean>> call, Response<BaseResponse<PostBean>> response) {

                    }

                    @Override
                    public void onFailure(Call<BaseResponse<PostBean>> call, Throwable t) {

                    }
                });

    }

}
