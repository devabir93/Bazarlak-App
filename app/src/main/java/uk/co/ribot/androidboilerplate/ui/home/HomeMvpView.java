package uk.co.ribot.androidboilerplate.ui.home;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.GetProductByIdResponseBody;
import uk.co.ribot.androidboilerplate.data.model.Mainvideo;
import uk.co.ribot.androidboilerplate.data.model.Offerproduct;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface HomeMvpView extends MvpView {


    void showVideo(Mainvideo mainvideo);

    void showOffers(List<Offerproduct> offerproducts);

    void showOfferProduct(GetProductByIdResponseBody productByIdResponseBody);
}
