package com.nezamipour.mehdi.digikala.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class UiUtil {

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
