package com.libs.customlibs;

public class CustomFontsUtilits {
    public static boolean vaildFontType(String fontName){
        if(fontName != null && (
                fontName.toLowerCase().endsWith(".ttf")||
                fontName.toLowerCase().endsWith(".otf")
                )){
            return true;
        }else{
            return false;
        }

    }
}
