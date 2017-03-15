package setpushchik.denis.testandersen.utils;

import android.view.View;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class ViewUtil {
    private ViewUtil() {
    }

    public static void hideViews(View... views) {
        for (View view : views) {
            changeVisibility(view, false);
        }
    }

    private static void changeVisibility(View view, boolean needToBeVisible) {
        if (view == null) {
            return;
        }
        int visibility = needToBeVisible ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    public static void showViews(View... views) {
        for (View view : views) {
            changeVisibility(view, true);
        }
    }
}
