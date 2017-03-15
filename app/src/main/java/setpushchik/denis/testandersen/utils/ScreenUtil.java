package setpushchik.denis.testandersen.utils;

import android.content.res.Resources;

/**
 * Created by Denis Stepushchik on 15.03.17.
 */

public class ScreenUtil {

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
