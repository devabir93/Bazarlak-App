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
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Brand;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.CategoryResponse;
import uk.co.ribot.androidboilerplate.data.model.ColorFeature;
import uk.co.ribot.androidboilerplate.data.model.Extracategory;
import uk.co.ribot.androidboilerplate.data.model.FilterDataResponse;
import uk.co.ribot.androidboilerplate.data.model.FilterSize;
import uk.co.ribot.androidboilerplate.data.model.Image;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductBody;
import uk.co.ribot.androidboilerplate.data.model.ProductResponse;
import uk.co.ribot.androidboilerplate.data.model.Size;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.data.model.LoginResponse;
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

    public UserData getCurrentUser() {
        UserData currentUser = getPreferencesHelper().getCurrentUser();
        return currentUser;
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
    public Observable<LoginResponse> makeLogin(String token, UserData userData) {

        return mBazarlakService.login(userData)
                .concatMap(new Function<LoginResponse, ObservableSource<? extends LoginResponse>>() {
                    @Override
                    public ObservableSource<? extends LoginResponse> apply(final LoginResponse loginResponse) throws Exception {

                        if (loginResponse.getStatus()) {
                            return Observable.create(new ObservableOnSubscribe<LoginResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<LoginResponse> e) throws Exception {
//                                    getPreferencesHelper().addUserSession(loginResponse);
                                    e.onNext(loginResponse);
                                    e.onComplete();
                                }
                            });
                        } else
                            return Observable.create(new ObservableOnSubscribe<LoginResponse>() {
                                @Override
                                public void subscribe(ObservableEmitter<LoginResponse> e) throws Exception {

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
                                    List<Category> categoryList = categoryResponse.getData().getCategories().getData();
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
//                                            for (Product product :
//                                                    category.getProducts()) {
//                                                product.setId(Long.valueOf(product.getProductId()));
//                                                product.save();
//                                                for (ColorFeature colorFeature :
//                                                        product.getColorFeatures()) {
//                                                    colorFeature.save();
//                                                    for (FilterSize size :
//                                                            colorFeature.getSizes()) {
//                                                        size.setId(Long.valueOf(size.getSizesId()));
//                                                        size.save();
//                                                    }
//                                                }
//                                            }

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

    public Observable<List<Product>> getProducts(final ProductBody productBody) {
        return mBazarlakService.getProduct(productBody)
                .concatMap(new Function<ProductResponse, ObservableSource<? extends List<Product>>>() {
                    @Override
                    public ObservableSource<? extends List<Product>> apply(final ProductResponse productResponse) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<List<Product>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<Product>> e) throws Exception {
                                try {
                                    for (Product product :
                                            productResponse.getProductData()) {
                                        product.setId(Long.valueOf(product.getProductId()));
                                        product.save();
                                        for (Image image :
                                                product.getImages()) {
                                            image.setId(Long.valueOf(image.getImageId()));
                                            image.save();
                                        }
                                        for (ColorFeature colorFeature :
                                                product.getColorFeatures()) {
                                            colorFeature.save();
                                            for (Size size :
                                                    colorFeature.getSizes()) {
                                                size.setId(Long.valueOf(size.getSizesId()));
                                                size.save();
                                            }
                                        }
                                    }
                                    e.onNext(productResponse.getProductData());
                                    e.onComplete();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                }
                            }
                        });
                    }
                });
    }

    public Observable<FilterDataResponse> getFiltersData() {
        return mBazarlakService.getFiltersData()
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

    private void closeDatabase() {
        sugarDb.close();
    }

    private void openDatabase() {
        sugarDb = new SugarDb(mContext);
        if (!sugarDb.getDB().isOpen())
            sugarDb.getReadableDatabase();
    }
}
