package com.example.mvc.network;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvc.MainActivity;
import com.example.mvc.bean.ApiRespond;
import com.example.mvc.bean.BaseResponse;
import com.example.mvc.bean.GetBean;
import com.example.mvc.bean.PostBean;

import java.util.List;

public class LiveDataHttpRepository extends BaseRepository {
     public MutableLiveData<List<GetBean>> liveDataResult=new MutableLiveData<>();
     public MutableLiveData<PostBean> livedataPost=new MutableLiveData<>();
    public void getLiveDataHttps(LifecycleOwner owner){
        mainApi.getHttps("6666666", 190000).observe(owner, new BasseObserver<List<GetBean>>(){
            @Override
            public void onResult(List<GetBean> result) {
                liveDataResult.postValue(result);
            }

            @Override
            public void onError(BaseResponse error) {

            }
        });
    }
    public void getLiveDataPost(LifecycleOwner owner,String keyword){
        mainApi.postJson("6666666",keyword).observe(owner,new BasseObserver<PostBean>(){

            @Override
            public void onResult(PostBean result) {
                livedataPost.postValue(result);
            }

            @Override
            public void onError(BaseResponse error) {

            }
        });
    }
}
