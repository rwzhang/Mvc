package com.example.mvc.network;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvc.bean.BaseResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * 请求结果返回处理
 * @author zhangrenwei
 */
public class LiveDataCallAdapter<T extends BaseResponse<T>> implements CallAdapter<T, LiveData<BaseResponse<T>>> {
    private Type respondtype;

    public LiveDataCallAdapter(Type respondtype) {
        this.respondtype = respondtype;
    }

    @Override
    public Type responseType() {
        return respondtype;
    }

    @Override
    public LiveData<BaseResponse<T>> adapt(final Call<T> call) {
        return new LiveData<BaseResponse<T>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<T>() {
                        @Override
                        public void onResponse(Call<T> call, Response<T> response) {
                            Log.d("zrw", "onResponse:请求耗时:" + (response.raw().receivedResponseAtMillis() - response.raw().sentRequestAtMillis()) + " ms");
                            if (response.isSuccessful()) {
//                                if (response.code() == 204 || response == null) {
//                                    postValue(new ApiRespond<>(response.body(), ApiRespond.Status.EMPTY, null));
//                                } else {
                                postValue(response.body());
//                                }
                            }else{
                                postValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<T> call, Throwable t) {

                        }
                    });
                }
            }
        };
    }


}
