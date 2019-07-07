package com.java.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil  {

    public static int getInt(HttpServletRequest request, String key){
        try {
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest request, String key){
        try{
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1l;
        }
    }

    public static Boolean getBoolean(HttpServletRequest request, String key){
        try {
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        try{
            String str = request.getParameter(key);
            if(str != null){
                str = str.trim();
            }
            if(str.equals("")){
                str = null;
            }
            return str;
        }catch (Exception e){
            return null;
        }
    }

}
