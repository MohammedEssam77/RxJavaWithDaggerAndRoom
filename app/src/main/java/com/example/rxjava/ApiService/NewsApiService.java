package com.example.rxjava.ApiService;

import com.example.rxjava.pojo.NewsRseponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsApiService {
    @Headers({
            "Content-type: application/json",
            "DeviceToken: 29A2506C-7C1C-40B9-8640-9ECA36659401",
            "AuthToken: dVMFDDAcZ1"
    })
    @GET("Api/News/GetNewsHome/209361/0/0/20/0")
     Observable<NewsRseponse>getNews();

}
