package cn.byk.pandora.libs.util;

import java.util.Locale;

/**
 * Created by Byk on 2018/7/1.
 **/
public class LanguageUtil {

    private static final int LANG_CN_CH = 0;
    private static final int LANG_CN_TW = 1;
    private static final int LANG_EN = 2;

    private static final String EN_START = "en-";
    private static final String LANG_CN_TW_VALUE = "zh-TW";

    private static final String LANGUAGE_EN = "enu";
    private static final String LANGUAGE_TW = "cht";
    private static final String LANGUAGE_CN = "chs";

    public static int getLang() {
        Locale locale = Locale.getDefault();
        String languageValue = String.format("%s-%s", locale.getLanguage(), locale.getCountry());
        int language = LANG_CN_CH;
        if (languageValue.startsWith(EN_START)) {
            language = LANG_EN;
        } else if (LANG_CN_TW_VALUE.equals(languageValue)) {
            language = LANG_CN_TW;
        }

        return language;
    }

    public static String getLangValue() {
        Locale locale = Locale.getDefault();
        String languageValue = String.format("%s-%s", locale.getLanguage(), locale.getCountry());
        String langValue = LANGUAGE_CN;
        if (languageValue.startsWith(EN_START)) {
            langValue = LANGUAGE_EN;
        } else if (LANG_CN_TW_VALUE.equals(languageValue)) {
            langValue = LANGUAGE_TW;
        }

        return langValue;
    }
}
