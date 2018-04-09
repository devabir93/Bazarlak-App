package uk.co.ribot.androidboilerplate.injection.component;

import dagger.Subcomponent;
import uk.co.ribot.androidboilerplate.injection.PerActivity;
import uk.co.ribot.androidboilerplate.injection.module.ActivityModule;
import uk.co.ribot.androidboilerplate.ui.bag.BagFragment;
import uk.co.ribot.androidboilerplate.ui.category.CategoryFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.filter.FilterActivity;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsFragment;
import uk.co.ribot.androidboilerplate.ui.home.HomeFragment;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;
import uk.co.ribot.androidboilerplate.ui.main_activity.MainActivity2;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileFragment;
import uk.co.ribot.androidboilerplate.ui.profile.login.LoginFragment;
import uk.co.ribot.androidboilerplate.ui.profile.register.RegisterFragment;
import uk.co.ribot.androidboilerplate.ui.search.SearchFragment;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(MainActivity2 mainActivity);
    void inject(HomeFragment homeFragment);
    void inject(CategoryFragment categoryFragment);
    void inject(SearchFragment searchFragment);
    void inject(ProfileFragment profileFragment);
    void inject(BagFragment bagFragment);
    void inject(SubCategoryFragment subCategoryFragment);
    void inject(ProductsFragment productsFragment);
    void inject(LoginFragment loginFragment);
    void inject(RegisterFragment registerFragment);
    void inject(FilterActivity filterActivity);
}
