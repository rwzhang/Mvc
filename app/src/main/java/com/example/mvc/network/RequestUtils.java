package com.example.mvc.network;

import androidx.lifecycle.LifecycleOwner;

import com.example.mvc.bean.UrlsBean;

public class RequestUtils {

    public static void getRobotVideo(LifecycleOwner owner, BaseObserver<UrlsBean> result) {
        RetrofitClient.getInstance("1").getApi().getGithubHttps().compose(RxHelper.observableIO2Main(owner))
                .subscribe(result);
    }

}
