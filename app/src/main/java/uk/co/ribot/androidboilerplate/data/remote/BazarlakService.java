package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.CategoryResponse;
import uk.co.ribot.androidboilerplate.data.model.FilterDataResponse;
import uk.co.ribot.androidboilerplate.data.model.FilterProductResponse;
import uk.co.ribot.androidboilerplate.data.model.GetProductByIdResponseBody;
import uk.co.ribot.androidboilerplate.data.model.HomePageResponse;
import uk.co.ribot.androidboilerplate.data.model.OrderData;
import uk.co.ribot.androidboilerplate.data.model.ProductResponse;
import uk.co.ribot.androidboilerplate.data.model.RestEmailBody;
import uk.co.ribot.androidboilerplate.data.model.RestPasswordBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.data.model.SaveOrdersResponse;
import uk.co.ribot.androidboilerplate.data.model.UpdateProfileResponse;
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
    Observable<RegisterResponse> login(@Body UserData userData);

    @POST("register")
    Observable<RegisterResponse> register(@Body UserData userData);

    @GET("category")
    Observable<CategoryResponse> getAllCategories();

    @GET("get_filter")
    Observable<FilterDataResponse> getFiltersData(@Query("subcategory") String subcategory, @Query("extracategory") String extracategory);

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

    @GET("get_products")
    Observable<ProductResponse> getProduct(@Query("category") String categoryId, @Query("subcategory") String subCategoryId,
                                           @Query("extracategory") String extracategoryId, @Query("page") String page);

    @GET("products/{query}")
    Observable<GetProductByIdResponseBody> getProductById(@Path("query") String productId);

    @GET("filter_product")
    Observable<FilterProductResponse> getfilteredProduct(@Query("subcategory") String subcategory, @Query("extracategory") String extracategory,
                                                         @Query("brand") String brand, @Query("color") String color,
                                                         @Query("size") String size, @Query("price") String price);


    @GET("homepage")
    Observable<HomePageResponse> getHomePage();

    @POST("add_cart")
    Observable<RestResponse> saveOrders(@Header("Authorization") String token, @Body OrderData orderData);

    //    @Headers({"Accept: application/json"
//            , "Accept-Language:en"
//            , ""})
    @POST("forget_password")
    Observable<RestResponse> forgotPassword(RestEmailBody restEmailBody);

    @POST("reset_password")
    Observable<RestResponse> resetPassword(@Header("Authorization") String token, @Body RestPasswordBody restPasswordBody);

    @POST("reset_email")
    Observable<RestResponse> resetEmail(@Header("Authorization") String token, @Body RestEmailBody restEmailBody);

    @POST("update")
    Observable<UpdateProfileResponse> updateProfile(@Header("Authorization") String token, @Body UserData userData);

    @POST("GetPayAddress")
    Observable<RestResponse> updateAddress(@Header("Authorization") String token, @Body AddressBody addressBody);

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
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            //.addHeader("Authorization", "Bearer " + )
                            //.addHeader("Accept-Language" ,"en")
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BazarlakService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(BazarlakService.class);
        }
    }
}
