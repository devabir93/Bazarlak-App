package uk.co.ribot.androidboilerplate.data;

import android.content.Context;

import com.google.common.collect.Lists;
import com.orm.SugarDb;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.CategoryResponse;
import uk.co.ribot.androidboilerplate.data.model.Extracategory;
import uk.co.ribot.androidboilerplate.data.model.FilterBody;
import uk.co.ribot.androidboilerplate.data.model.FilterDataResponse;
import uk.co.ribot.androidboilerplate.data.model.FilterProductResponse;
import uk.co.ribot.androidboilerplate.data.model.GetProductByIdResponseBody;
import uk.co.ribot.androidboilerplate.data.model.HomePageData;
import uk.co.ribot.androidboilerplate.data.model.HomePageResponse;
import uk.co.ribot.androidboilerplate.data.model.PaymentDataBody;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.data.model.OrderData;
import uk.co.ribot.androidboilerplate.data.model.ProductResponse;
import uk.co.ribot.androidboilerplate.data.model.Products;
import uk.co.ribot.androidboilerplate.data.model.RestEmailBody;
import uk.co.ribot.androidboilerplate.data.model.RestPasswordBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;
import uk.co.ribot.androidboilerplate.data.model.UpdateProfileResponse;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.BazarlakService;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

@Singleton
public class DataManager {

    private final BazarlakService mBazarlakService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    protected SugarDb sugarDb;
    Context mContext;
    String token;

    @Inject
    public DataManager(BazarlakService bazarlakService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, @ApplicationContext Context context) {
        mBazarlakService = bazarlakService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mContext = context;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public String getToken() {
        if (getPreferencesHelper().getCurrentUser().getAccessToken() != null &&
                !getPreferencesHelper().getCurrentUser().getAccessToken().isEmpty()) {
            token = "Bearer " + getPreferencesHelper().getCurrentUser().getAccessToken();
        }
        Timber.d("token %s", token);
        return token;
    }

    public Observable<Ribot> syncRibots() {
        return mBazarlakService.getRibots()
                .concatMap(new Function<List<Ribot>, ObservableSource<? extends Ribot>>() {
                    @Override
                    public ObservableSource<? extends Ribot> apply(@NonNull List<Ribot> ribots)
                            throws Exception {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    @android.support.annotation.NonNull
    public Observable<RegisterResponse> makeLogin(UserData userData) {

        return mBazarlakService.login(userData)
                .concatMap(new Function<RegisterResponse, ObservableSource<? extends RegisterResponse>>() {
                    @Override
                    public ObservableSource<? extends RegisterResponse> apply(final RegisterResponse loginResponse) throws Exception {

                        if (loginResponse.getStatus()) {
                            return Observable.create(new ObservableOnSubscribe<RegisterResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<RegisterResponse> e) throws Exception {
                                    getPreferencesHelper().clear();
                                    getPreferencesHelper().addUserSession(loginResponse.getUserData());
                                    Timber.d("loginResponse.getUserData() %s", loginResponse.getUserData().getAccessToken());
                                    e.onNext(loginResponse);
                                    e.onComplete();
                                }
                            });
                        } else
                            return Observable.create(new ObservableOnSubscribe<RegisterResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<RegisterResponse> e) throws Exception {

                                    e.onNext(loginResponse);
                                    e.onComplete();
                                }
                            });
                    }
                });
    }

    public Observable<RegisterResponse> makeRegister(UserData userData) {

        return mBazarlakService.register(userData)
                .concatMap(new Function<RegisterResponse, ObservableSource<? extends RegisterResponse>>() {
                    @Override
                    public ObservableSource<? extends RegisterResponse> apply(final RegisterResponse response) throws Exception {

                        if (response.getStatus()) {
                            return Observable.create(new ObservableOnSubscribe<RegisterResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<RegisterResponse> e) throws Exception {
                                    getPreferencesHelper().addUserSession(response.getUserData());
                                    e.onNext(response);
                                    e.onComplete();
                                }
                            });
                        } else
                            return Observable.create(new ObservableOnSubscribe<RegisterResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<RegisterResponse> e) throws Exception {

                                    e.onNext(response);
                                    e.onComplete();
                                }
                            });
                    }
                });
    }

    public Observable<RestResponse> resetPassword(RestPasswordBody restPasswordBody) {
        return mBazarlakService.resetPassword(getToken(), restPasswordBody)
                .concatMap(new Function<RestResponse, ObservableSource<? extends RestResponse>>() {
                    @Override
                    public ObservableSource<? extends RestResponse> apply(final RestResponse restResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<RestResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<RestResponse> e) throws Exception {
                                try {
                                    e.onNext(restResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }

                            }
                        });
                    }
                });
    }

    public Observable<RestResponse> resetEmail(RestEmailBody restEmailBody) {
        return mBazarlakService.resetEmail(getToken(), restEmailBody)
                .concatMap(new Function<RestResponse, ObservableSource<? extends RestResponse>>() {
                    @Override
                    public ObservableSource<? extends RestResponse> apply(final RestResponse restResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<RestResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<RestResponse> e) throws Exception {
                                try {
                                    e.onNext(restResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }

                            }
                        });
                    }
                });
    }

    public Observable<RestResponse> forgotPassword(RestEmailBody restEmailBody) {
        return mBazarlakService.forgotPassword(restEmailBody)
                .concatMap(new Function<RestResponse, ObservableSource<? extends RestResponse>>() {
                    @Override
                    public ObservableSource<? extends RestResponse> apply(final RestResponse restResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<RestResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<RestResponse> e) throws Exception {
                                try {
                                    e.onNext(restResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }

                            }
                        });
                    }
                });
    }

    public Observable<UpdateProfileResponse> updateProfile(UserData userData) {
        return mBazarlakService.updateProfile(getToken(), userData)
                .concatMap(new Function<UpdateProfileResponse, ObservableSource<? extends UpdateProfileResponse>>() {
                    @Override
                    public ObservableSource<? extends UpdateProfileResponse> apply(final UpdateProfileResponse updateProfileResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<UpdateProfileResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<UpdateProfileResponse> e) throws Exception {
                                try {
                                    e.onNext(updateProfileResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<RestResponse> updateAddress(AddressBody addressBody) {
        return mBazarlakService.updateAddress(getToken(), addressBody)
                .concatMap(new Function<RestResponse, ObservableSource<? extends RestResponse>>() {
                    @Override
                    public ObservableSource<? extends RestResponse> apply(final RestResponse restResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<RestResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<RestResponse> e) throws Exception {
                                try {
                                    Timber.d("updateAddress %s", restResponse.toString());
                                    e.onNext(restResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<CategoryResponse> syncCategories() {
        return mBazarlakService.getAllCategories()
                .concatMap(new Function<CategoryResponse, ObservableSource<? extends CategoryResponse>>() {
                    @Override
                    public ObservableSource<? extends CategoryResponse> apply(@NonNull final CategoryResponse categoryResponse)
                            throws Exception {
                        return Observable.create(new ObservableOnSubscribe<CategoryResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<CategoryResponse> e) throws Exception {
                                try {
                                    openDatabase();
                                    List<Category> categoryList = categoryResponse.getCategoriesData().getCategories().getData();
                                    if (categoryList.size() > 0) {
                                        for (Category category :
                                                categoryList) {
                                            category.setId(Long.valueOf(category.getCategoryId()));

                                            for (Subcategory subcategory :
                                                    category.getSubcategory()) {
                                                subcategory.setId(Long.valueOf(subcategory.getSubCategoryId()));
                                                subcategory.save();
                                                for (Extracategory Extracategory :
                                                        subcategory.getExtracategory()) {
                                                    Extracategory.setId(Long.valueOf(Extracategory.getExtracategoryId()));
                                                    Extracategory.save();
                                                }
                                            }
                                            category.save();
                                        }
                                    }
                                    closeDatabase();
                                    e.onNext(categoryResponse);
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<List<Category>> getCategories() {

        return Observable.create(new ObservableOnSubscribe<List<Category>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Category>> e) throws Exception {
                try {
                    openDatabase();
                    Iterator<Category> categoryIterator = Category.findAll(Category.class);
                    List<Category> categories = Lists.newArrayList(categoryIterator);

                    closeDatabase();
                    e.onNext(categories);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        });
    }

    public Observable<List<Subcategory>> getSubCategories(final Integer categoryId) {

        return Observable.create(new ObservableOnSubscribe<List<Subcategory>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Subcategory>> e) throws Exception {
                openDatabase();
                List<Subcategory> subcategories = Subcategory.find(Subcategory.class, "CAT_ID = ? ", new String[]{String.valueOf(categoryId)});
                closeDatabase();
                Timber.d("Subcategory %s", subcategories);
                try {
                    e.onNext(subcategories);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        });
    }

    public Observable<List<Extracategory>> getExtraSubCategories(final String subCategoryId) {

        return Observable.create(new ObservableOnSubscribe<List<Extracategory>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Extracategory>> e) throws Exception {
                openDatabase();
                List<Extracategory> extraSubcategories = Extracategory.find(Extracategory.class, "subcategory_id = ? ", subCategoryId);
                closeDatabase();
                Timber.d("getExtraSubCategories %s", extraSubcategories);
                try {
                    e.onNext(extraSubcategories);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        });
    }

    public Observable<ProductResponse> getProducts(String categoryId, String subCategoryId, String extracategoryId, String page) {
        Timber.d("categoryId %s subCategoryId%s ExtracategoryId%s", categoryId, subCategoryId, extracategoryId);
        if (extracategoryId == null || extracategoryId.equals("null"))
            extracategoryId = "";
        Timber.d("categoryId %s subCategoryId%s ExtracategoryId%s", categoryId, subCategoryId, extracategoryId);

        return mBazarlakService.getProduct(categoryId, subCategoryId, extracategoryId, page)
                .concatMap(new Function<ProductResponse, ObservableSource<? extends ProductResponse>>() {
                    @Override
                    public ObservableSource<? extends ProductResponse> apply(final ProductResponse productResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<ProductResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<ProductResponse> e) throws Exception {
                                try {
                                    Timber.d("productResponse %s", productResponse.toString());
//                                    for (Product product :
//                                            productResponse.getProductData()) {
//                                        product.setId(Long.valueOf(product.getProductId()));
//                                        product.save();
//                                        for (Image image :
//                                                product.getImages()) {
//                                            image.setId(Long.valueOf(image.getImageId()));
//                                            image.save();
//                                        }
//
//                                        for (ProductFeature productFeature :
//                                                product.getColorFeatures()) {
//                                            productFeature.setId(Long.valueOf(productFeature.getProductFeatureId()));
//                                            productFeature.save();
//                                        }
//                                    }
                                    e.onNext(productResponse);
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<GetProductByIdResponseBody> getProductById(String productId) {
        return mBazarlakService.getProductById(productId)
                .concatMap(new Function<GetProductByIdResponseBody, ObservableSource<? extends GetProductByIdResponseBody>>() {
                    @Override
                    public ObservableSource<? extends GetProductByIdResponseBody> apply(final GetProductByIdResponseBody productResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<GetProductByIdResponseBody>() {
                            @Override
                            public void subscribe(ObservableEmitter<GetProductByIdResponseBody> e) throws Exception {
                                try {
                                    Timber.d("productResponse %s", productResponse);

                                    e.onNext(productResponse);
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<FilterDataResponse> getFiltersData(String subcategory, String extracategory) {
        return mBazarlakService.getFiltersData(subcategory, extracategory)
                .concatMap(new Function<FilterDataResponse, ObservableSource<? extends FilterDataResponse>>() {
                    @Override
                    public ObservableSource<? extends FilterDataResponse> apply(@NonNull final FilterDataResponse brandResponse)
                            throws Exception {
                        return Observable.create(new ObservableOnSubscribe<FilterDataResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<FilterDataResponse> e) throws Exception {
                                try {
                                    e.onNext(brandResponse);
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<FilterProductResponse> getFilteredProducts(final FilterBody filterBody) {
        Timber.d("getFilteredProducts2");
        return mBazarlakService.getfilteredProduct(filterBody.getCategory(), filterBody.getExtracategory(), filterBody.getBrand(), filterBody.getColor(), filterBody.getSize(), filterBody.getPrice())
                .concatMap(new Function<FilterProductResponse, ObservableSource<? extends FilterProductResponse>>() {
                    @Override
                    public ObservableSource<? extends FilterProductResponse> apply(final FilterProductResponse filterProductResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<FilterProductResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<FilterProductResponse> e) throws Exception {
                                Timber.d("filterProductResponse %s", filterProductResponse);
                                try {
                                    if (filterProductResponse.getStatus())
                                        e.onNext(filterProductResponse);
                                    else
                                        e.onNext(null);
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<HomePageData> getHomePage() {
        return mBazarlakService.getHomePage()
                .concatMap(new Function<HomePageResponse, ObservableSource<? extends HomePageData>>() {
                    @Override
                    public ObservableSource<? extends HomePageData> apply(final HomePageResponse homePageResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<HomePageData>() {
                            @Override
                            public void subscribe(ObservableEmitter<HomePageData> e) throws Exception {
                                Timber.d("homePageResponse %s", homePageResponse.toString());
                                if (homePageResponse.getStatus()) {
                                    e.onNext(homePageResponse.getHomePageData());
                                } else
                                    e.onNext(null);
                                e.onComplete();
                            }
                        });
                    }
                });
    }

    public Observable<RestResponse> saveOrders(OrderData orderData) {
        return mBazarlakService.saveOrders(getToken(), orderData)
                .concatMap(new Function<RestResponse, ObservableSource<? extends RestResponse>>() {
                    @Override
                    public ObservableSource<? extends RestResponse> apply(final RestResponse restResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<RestResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<RestResponse> e) throws Exception {
                                try {
                                    Timber.d("restResponse %s", restResponse);
                                    e.onNext(restResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<Boolean> addTobag(final ProductOrder productOrder) {
        Timber.d("productOrder %s", productOrder);
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> subscriber) throws Exception {
                try {
                    boolean isSavedInDatabase = saveOrderWithoutRX(productOrder);
                    Timber.d("isSavedInDatabase %s", isSavedInDatabase);

                    subscriber.onNext(isSavedInDatabase);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onNext(false);
                    subscriber.onError(e);
                }
            }
        });

    }

    public Boolean saveOrderWithoutRX(ProductOrder productOrder) {
        boolean isSavedInDatabase = false;
        Timber.d("saveOrderWithoutRX");

        List<ProductOrder> productOrderList;

        productOrderList = ProductOrder.find(ProductOrder.class, "product_id=? and product_feature=?", new String[]{String.valueOf(productOrder.getProductId()), String.valueOf(productOrder.getProductFeature())});
        if (productOrderList.size() > 0) {
            Timber.d("saved productOrder");
            productOrderList.get(0).setQuantity(productOrder.getQuantity());
            productOrderList.get(0).setProductFeature(productOrder.getProductFeature());
            productOrderList.get(0).setProductId(productOrder.getProductId());
            productOrderList.get(0).setProduct(productOrder.getProduct());
            productOrderList.get(0).save();
            if (productOrderList.get(0).save() > 0)
                isSavedInDatabase = true;
        } else {
            productOrder.setProductId(productOrder.getProductId());
            productOrder.setId(Long.valueOf(productOrder.getProductId()));
            productOrder.setProductFeature(productOrder.getProductFeature());
            productOrder.setProduct(productOrder.getProduct());
            Product product = productOrder.getProduct();
            product.setId(Long.valueOf(productOrder.getProduct().getProductId()));
            product.save();
            productOrder.save();
            if (productOrder.save() > 0) {
                isSavedInDatabase = true;
                Timber.d("save productOrder %s", productOrder.getProductId());
            }

        }
        return isSavedInDatabase;

    }

    public Observable<List<ProductOrder>> getSavedOrders() {

        return Observable.create(new ObservableOnSubscribe<List<ProductOrder>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ProductOrder>> e) throws Exception {
                openDatabase();
                Iterator<ProductOrder> productOrderIterator = ProductOrder.findAll(ProductOrder.class);
                List<ProductOrder> productOrderList = Lists.newArrayList(productOrderIterator);
                closeDatabase();
                Timber.d("productOrderList %s", productOrderList);
                try {
                    e.onNext(productOrderList);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        });
    }

    public Observable<Products> getSearchResult(String key, String page) {
        return mBazarlakService.getSearchResult(key, key)
                .concatMap(new Function<ProductResponse, ObservableSource<? extends Products>>() {
                    @Override
                    public ObservableSource<? extends Products> apply(final ProductResponse productResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Products>() {
                            @Override
                            public void subscribe(ObservableEmitter<Products> e) throws Exception {
                                try {
                                    Timber.d("productResponse %s", productResponse);
                                    if (productResponse.getStatus())
                                        e.onNext(productResponse.getData().getProducts());

                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<RestResponse> setPaymentData(PaymentDataBody paymentDataBody) {
        return mBazarlakService.setPaymentData(getToken(), paymentDataBody)
                .concatMap(new Function<RestResponse, ObservableSource<? extends RestResponse>>() {
                    @Override
                    public ObservableSource<? extends RestResponse> apply(final RestResponse restResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<RestResponse>() {
                            @Override
                            public void subscribe(ObservableEmitter<RestResponse> e) throws Exception {
                                try {
                                    Timber.d("setPaymentData %s", restResponse.toString());
                                    e.onNext(restResponse);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                            }
                        });
                    }
                });
    }

    private void closeDatabase() {
        sugarDb.close();
    }

    private void openDatabase() {
        sugarDb = new SugarDb(mContext);
        if (!sugarDb.getDB().isOpen())
            sugarDb.getReadableDatabase();
    }
}
