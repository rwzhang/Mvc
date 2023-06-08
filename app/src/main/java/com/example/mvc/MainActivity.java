package com.example.mvc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.mvc.bean.BaseResponse;
import com.example.mvc.bean.UrlsBean;
import com.example.mvc.network.BaseObserver;
import com.example.mvc.network.RetrofitClient;
import com.example.mvc.network.RxHelper;
import com.google.gson.Gson;
import com.hjq.gson.factory.GsonFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String  test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRobotVideo();
    }

    public void getRobotVideo() {
        String gson=new Gson().toJson("{\"code\":200,\"message\":\"操作成功\",\"data\":[{\"name\":\"设备首页视频\",\"code\":\"ROBOT_HOMEPAGE_VIDEO\",\"icon\":\"\",\"content\":\"http://resources.bbqtime.com.cn/video/%E6%9C%89%E8%83%8C%E6%99%AF%E9%9F%B3%E6%A8%82%E5%AE%8C%E6%88%90%E5%93%81%E5%A4%A7%E5%B1%8F%E5%B9%95.mp4\"}],\"success\":true}");
        UrlsBean bean=GsonFactory.getSingletonGson().fromJson(gson, UrlsBean.class);
        new Handler(Looper.myLooper()).post(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    Log.d("zrw","第二个线程访问"+i);
                    if(i/2==0){
                        RetrofitClient.getInstance("0").getApi().getGithubHttps().compose(RxHelper.observableIO2Main(MainActivity.this))
                                .subscribe(new BaseObserver<UrlsBean>(){

                                    @Override
                                    public void onSuccess(List<UrlsBean> result) {
                                        if(result!=null){

                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable e, String errorMsg) {

                                    }
                                });
                    }else{
                        RetrofitClient.getInstance("1").getApi().getGithubHttp().observe(MainActivity.this, new Observer<BaseResponse<UrlsBean>>() {
                            @Override
                            public void onChanged(BaseResponse<UrlsBean> urlsBeanBaseResponse) {
                                if(urlsBeanBaseResponse!=null){

                                }
                            }
                        });

                    }
                }
            }
        });
        new Handler(Looper.myLooper()).post(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    Log.d("zrw","第一个线程访问"+i);
                    if(i/2==0){
                        RetrofitClient.getInstance("0").getApi().getGithubHttps().compose(RxHelper.observableIO2Main(MainActivity.this))
                                .subscribe(new BaseObserver<UrlsBean>(){

                                    @Override
                                    public void onSuccess(List<UrlsBean> result) {
                                        if(result!=null){

                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable e, String errorMsg) {

                                    }
                                });
                    }else{
                        RetrofitClient.getInstance("1").getApi().getGithubHttp().observe(MainActivity.this, new Observer<BaseResponse<UrlsBean>>() {
                            @Override
                            public void onChanged(BaseResponse<UrlsBean> urlsBeanBaseResponse) {
                                if(urlsBeanBaseResponse!=null){

                                }
                            }
                        });

                    }
                }
            }
        });

    }
}