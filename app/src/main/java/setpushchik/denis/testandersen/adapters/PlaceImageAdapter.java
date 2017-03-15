package setpushchik.denis.testandersen.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import setpushchik.denis.testandersen.ui.ImageGridLayoutManager;
import setpushchik.denis.testandersen.R;
import setpushchik.denis.testandersen.network.model.Image;
import setpushchik.denis.testandersen.utils.PicassoBigCache;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class PlaceImageAdapter extends RecyclerView.Adapter<PlaceImageAdapter.ImageViewHolder> {

    private List<Image> mImages = new ArrayList<>();

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(getImage(position), position);
    }

    private Image getImage(int position) {
        return mImages.get(position);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setImages(List<Image> images) {
        clear();
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    public void clear() {
        mImages.clear();
        notifyDataSetChanged();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView mImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Image image, int position) {
            bindImage(image, position);
        }

        private void bindImage(Image image, int position) {
            String url = image.getSource().getUrl();

            if (!TextUtils.isEmpty(url)) {
                int width = ImageGridLayoutManager.getWidthOfImage(position);
                int height = itemView.getResources().getDimensionPixelSize(R.dimen.height_of_image);
                loadImage(url, width, height);
            }
        }

        private void loadImage(String url, int width, int height) {
            PicassoBigCache.INSTANCE.getPicassoBigCache(itemView.getContext())
                    .load(url)
                    .centerCrop()
                    .resize(width, height)
                    .into(mImage);
        }
    }

}
