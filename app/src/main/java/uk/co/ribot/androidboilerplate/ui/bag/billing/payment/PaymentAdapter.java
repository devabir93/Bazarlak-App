package uk.co.ribot.androidboilerplate.ui.bag.billing.payment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CvvEditText;
import com.braintreepayments.cardform.view.ExpirationDateEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.PaymentDataBody;
import uk.co.ribot.androidboilerplate.util.CheckValidity;
import uk.co.ribot.androidboilerplate.util.EventPosterHelper;

import static uk.co.ribot.androidboilerplate.util.ViewUtil.collapse;
import static uk.co.ribot.androidboilerplate.util.ViewUtil.expand;

public class PaymentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity mActivity;

    List<Integer> integers;
    HandleOnclick mHandleOnclick;


    void setData(Activity activty, List<Integer> integers, HandleOnclick handleOnclick) {
        mActivity = activty;
        this.integers = integers;
        mHandleOnclick = handleOnclick;
    }

    @Inject
    public PaymentAdapter() {
        integers = new ArrayList<>();
    }

    class ViewHolderVisa extends RecyclerView.ViewHolder {
        @BindView(R.id.arrow_up)
        ImageView arrowUp;
        @BindView(R.id.header_layout)
        RelativeLayout headerLayout;
        @BindView(R.id.input_cardNo)
        CardEditText inputCardNo;
        @BindView(R.id.input_expire)
        ExpirationDateEditText inputExpire;
        @BindView(R.id.input_cvv)
        CvvEditText inputCvv;
        @BindView(R.id.input_card_name)
        EditText inputCardName;
        @BindView(R.id.checkbox_register)
        CheckBox checkboxRegister;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;
        String cardNo, cardName, cardCvv, cardExpire, isDefault;
        PaymentDataBody paymentDataBody;

        public ViewHolderVisa(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            EventBus.getDefault().register(this);
        }

        public void setItemData(Activity mActivity, HandleOnclick mHandleOnclick) {
            contentLayout.setVisibility(View.GONE);
            headerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggle_contents();
                }
            });
            inputCardNo.addTextChangedListener(mTextWatcher);
            inputExpire.addTextChangedListener(mTextWatcher);
            inputCvv.addTextChangedListener(mTextWatcher);
            inputCardName.addTextChangedListener(mTextWatcher);

        }

        private TextWatcher mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // check Fields For Empty Values
                // checkFieldsForEmptyValues();
            }
        };

        @Subscribe
        public void checkFieldsForEmptyValues(CheckValidity checkValidity) {

            cardName = inputCardName.getText().toString();
            if (inputCardNo.isValid())
                cardNo = inputCardNo.getText().toString();
            if (inputCvv.isValid())
                cardCvv = inputCvv.getText().toString();
            if (inputExpire.isValid())
                cardExpire = inputExpire.getText().toString();
            Timber.d("cardExpire %s",cardExpire);

            boolean b = checkboxRegister.isChecked();
            if (b)
                isDefault = "1";
            else
                isDefault = "0";
            if (!cardName.isEmpty() && !cardNo.isEmpty() && !cardExpire.isEmpty() && !cardExpire.isEmpty() && !isDefault.isEmpty()) {
                mHandleOnclick.setEnabled(true);
                paymentDataBody = new PaymentDataBody();
                paymentDataBody.setCardNumber(cardNo);
                paymentDataBody.setName(cardName);
                paymentDataBody.setCvc(cardCvv);
                paymentDataBody.setExpiration(cardExpire);
                paymentDataBody.setIsdefault(isDefault);
                mHandleOnclick.onSaveClick(paymentDataBody);
            } else
                mHandleOnclick.setEnabled(false);

        }

        void toggle_contents() {
            if (contentLayout.isShown()) {
                collapse(contentLayout);
                contentLayout.setVisibility(View.GONE);

            } else {
                contentLayout.setVisibility(View.VISIBLE);
                arrowUp.setImageResource(R.drawable.ic_less);
                expand(contentLayout);
            }
        }

    }

    class ViewHolderPaypal extends RecyclerView.ViewHolder {

        public ViewHolderPaypal(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItemData() {
        }
    }

    @Override
    public int getItemViewType(int position) {
        return integers.get(position);
    }

    @Override
    public int getItemCount() {
//        return (mQuestions != null) ? mQuestions.size() : 0;
        return integers.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View itemView1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_visa, parent, false);
                return new ViewHolderVisa(itemView1);
            case 2:
                View itemView2 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_paypal, parent, false);
                return new ViewHolderPaypal(itemView2);
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderVisa)
            ((ViewHolderVisa) holder).setItemData(mActivity, mHandleOnclick);
        else if (holder instanceof ViewHolderPaypal)
            ((ViewHolderPaypal) holder).setItemData();

    }


    public interface HandleOnclick {
        void onSaveClick(PaymentDataBody paymentDataBody);

        // void checkFieldsForEmptyValues();
        void setEnabled(boolean b);
    }
}
