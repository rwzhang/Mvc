package com.example.mvc.network;



import android.content.Intent;

import com.example.mvc.bean.BaseResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 数据返回统一处理
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";
    @Override
    public void onNext(BaseResponse<T> response) {
        //在这边对 基础数据 进行统一处理
        if(response!=null){
            onSuccess(response.getData());
        }
    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    public abstract void onSuccess(List<T> result);

    public abstract void onFailure(Throwable e,String errorMsg);
    public void onNeedEmail(){};

}

