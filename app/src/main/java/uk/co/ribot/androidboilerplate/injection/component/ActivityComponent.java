package uk.co.ribot.androidboilerplate.injection.component;

import dagger.Subcomponent;
import uk.co.ribot.androidboilerplate.injection.PerActivity;
import uk.co.ribot.androidboilerplate.injection.module.ActivityModule;
import uk.co.ribot.androidboilerplate.ui.bag.BagFragment;
import uk.co.ribot.androidboilerplate.ui.bag.address.AddressFragment;
import uk.co.ribot.androidboilerplate.ui.bag.billing.BillingAndShippingFragment;
import uk.co.ribot.androidboilerplate.ui.bag.billing.payment.PaymentFragment;
import uk.co.ribot.androidboilerplate.ui.category.CategoryFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.filter.FilterActivity;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.SubCategoryFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsDetailsFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsFragment;
import uk.co.ribot.androidboilerplate.ui.home.HomeFragment;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;
import uk.co.ribot.androidboilerplate.ui.main_activity.MainActivity2;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_order.YourOrderActivity;
import uk.co.ribot.androidboilerplate.ui.profile.your_order.YourOrdersFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.AccountInfoFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.ChangeEmailFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.ChangePasswordFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.PaymentDetailsActivity;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.PaymentDetailsActivityFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.ShippingAddressActivityFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.YourProfileFragment;
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

    void inject(AddressFragment addressFragment);

    void inject(BillingAndShippingFragment checkoutFragment);

    void inject(ProductsDetailsFragment productsDetailsFragment);

    void inject(YourProfileFragment yourProfileFragment);

    void inject(AccountInfoFragment accountInfoFragment);

    void inject(ChangeEmailFragment changeEmailFragment);

    void inject(ChangePasswordFragment changePasswordFragment);

    void inject(PaymentDetailsActivity paymentDetailsActivity);

    void inject(PaymentDetailsActivityFragment paymentDetailsActivityFragment);

    void inject(ShippingAddressActivityFragment shippingAddressActivityFragment);

    void inject(YourOrderActivity yourOrderActivity);

    void inject(YourOrdersFragment yourOrdersFragment);

    void inject(PaymentFragment paymentFragment);
}
