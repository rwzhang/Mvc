package com.example.mvc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.mvc.bean.GetBean;
import com.example.mvc.bean.PostBean;
import com.example.mvc.repository.LiveDataHttpRepository;
import com.example.mvc.viewmodel.RequestModel;
import com.example.mvc.network.ResultListener;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String test = "测试上传测试";
    RequestModel requestModel = new RequestModel();
    LiveDataHttpRepository repository=new LiveDataHttpRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRobotVideo();
    }

    public void getRobotVideo() {
        //livedata请求
        repository.getLiveDataHttps(MainActivity.this);
        repository.liveDataResult.observe(MainActivity.this, new Observer<List<GetBean>>() {
            @Override
            public void onChanged(List<GetBean> getBeans) {
                if(getBeans!=null){
                    Log.d("zrw", "onResult: livedata请求到了数据"+new Gson().toJson(getBeans));
                }

            }
        });
        repository.getLiveDataPost(MainActivity.this,"鸿洋");
        repository.livedataPost.observe(MainActivity.this, new Observer<PostBean>() {
            @Override
            public void onChanged(PostBean postBean) {
                if(postBean!=null){

                }
            }
        });
        //okhttp+Rxjava请求
        requestModel.getHttp(MainActivity.this, new ResultListener<List<GetBean>>() {
            @Override
            public void onResult(List<GetBean> result) {
                if (result != null) {
                    Log.d("zrw", "onResult: Retrofit请求到了数据"+new Gson().toJson(result));
                }
            }

            @Override
            public void onError(String message) {

            }
        });
        requestModel.postJsons(MainActivity.this,"鸿洋",new ResultListener<PostBean>(){

            @Override
            public void onResult(PostBean result) {
                if(result!=null){

                }

            }

            @Override
            public void onError(String message) {

            }
        });



    }
}