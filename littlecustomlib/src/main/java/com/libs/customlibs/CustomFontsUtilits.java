package com.libs.customlibs;

public class CustomFontsUtilits {
    public static boolean vaildFontType(String fontName){
        return fontName != null && (fontName.toLowerCase().endsWith(".ttf") ||
                        fontName.toLowerCase().endsWith(".otf"));

    }
}
