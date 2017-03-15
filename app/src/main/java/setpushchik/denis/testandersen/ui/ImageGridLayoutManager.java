package setpushchik.denis.testandersen.ui;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import setpushchik.denis.testandersen.utils.ScreenUtil;

/**
 * Created by Denis Stepushchik on 15.03.17.
 */

public class ImageGridLayoutManager extends GridLayoutManager {

    public static final int MAX_NUMBER_OF_COLUMNS = 2;

    public ImageGridLayoutManager(Context context) {
        super(context, MAX_NUMBER_OF_COLUMNS);

        init();
    }

    private void init() {
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return getCustomSpanSize(position);
            }
        });
    }

    private static int getCustomSpanSize(int position) {
        if (position % 3 == 0) {
            return MAX_NUMBER_OF_COLUMNS;
        } else {
            return 1;
        }
    }

    public static int getWidthOfImage(int position) {
        int screenWidth = ScreenUtil.getScreenWidth();
        int spanSize = getCustomSpanSize(position);
        return screenWidth * spanSize / MAX_NUMBER_OF_COLUMNS;
    }
}
