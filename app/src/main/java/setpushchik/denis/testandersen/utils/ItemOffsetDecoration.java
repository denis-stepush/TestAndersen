package setpushchik.denis.testandersen.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Denis Stepushchik on 15.03.17.
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int pxOffset;

    public ItemOffsetDecoration(int dpOffset) {
        this.pxOffset = ScreenUtil.dpToPx(dpOffset);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = pxOffset;
        outRect.right = pxOffset;
        outRect.bottom = pxOffset;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = pxOffset;
        }

    }
}
