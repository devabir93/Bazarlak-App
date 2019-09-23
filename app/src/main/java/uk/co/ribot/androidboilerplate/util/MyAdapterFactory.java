package uk.co.ribot.androidboilerplate.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;
import com.google.gson.TypeAdapter;
@GsonTypeAdapterFactory
public abstract class MyAdapterFactory implements TypeAdapterFactory {

    // Static factory method to access the package
    // private generated implementation
//    public static TypeAdapterFactory create() {
//        return new AutoValueGson_MyAdapterFactory();
//    }

}