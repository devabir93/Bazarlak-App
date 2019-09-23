package uk.co.ribot.androidboilerplate.util;

public enum Language {
    KEY("lang"), ARABIC("ar"), ENGLISH("en");

    private String language;

    private Language(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return language.toString();
    }

    public static String KEY(){
        return KEY.toString();
    }
    public static String ARABIC(){
        return ARABIC.toString();
    }
    public static String ENGLISH(){
        return ENGLISH.toString();
    }
}
