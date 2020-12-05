package com.nezamipour.mehdi.digikala.util;

public class StringUtil {

    //TODO : complete more later
    public static boolean checkInputs(String firstName, String lastName, String password) {
        boolean result = true;
        if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || password.trim().isEmpty())
            result = false;
        if (password.trim().contains(" "))
            result = false;
        return result;
    }

}
