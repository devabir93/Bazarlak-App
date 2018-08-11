package uk.co.ribot.androidboilerplate.util;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Provides helper methods to post event to  greenrobot event bus
 */
public class EventPosterHelper {

    private static EventBus mBus;

    @Inject
    public EventPosterHelper(EventBus bus) {
        mBus = bus;
    }

    public static EventBus getEventBus() {
        return mBus;
    }

//    /**
//     * Helper method to post an event from a different thread to the main one.
//     */
//    public void postEventSafely(final Object event) {
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                mBus.post(event);
//            }
//        });
//    }
}
