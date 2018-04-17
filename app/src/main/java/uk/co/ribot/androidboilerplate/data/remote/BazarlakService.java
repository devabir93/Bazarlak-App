package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uk.co.ribot.androidboilerplate.data.model.BrandResponse;
import uk.co.ribot.androidboilerplate.data.model.CategoryResponse;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.data.model.LoginResponse;
import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.util.MyGsonTypeAdapterFactory;

public interface BazarlakService {

    //    String ENDPOINT = "https://api.ribot.io/";
    String ENDPOINT = "http://bazarlak.com/api/v1/";
    String IMAGE_URL = "http://bazarlak.com/";

    @POST("login")
    Observable<LoginResponse> login(@Body UserData userData);


    @POST("register")
    Observable<RegisterResponse> register(@Body UserData userData);

    @GET("category")
    Observable<CategoryResponse> getAllCategories();

    @GET("brand")
    Observable<BrandResponse> getBrands();

//    @GET("subcategory")
//    Observable<SubCategoryResponse> getAllSubCateories();

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static BazarlakService newRibotsService() {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
            gsonBuilder.setLenient();
            gsonBuilder.registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create());
            Gson gson = gsonBuilder.create();
//            Gson gson = new GsonBuilder()
//                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BazarlakService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(BazarlakService.class);
        }
    }
}
