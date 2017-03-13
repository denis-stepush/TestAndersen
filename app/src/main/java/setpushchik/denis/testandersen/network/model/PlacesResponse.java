package setpushchik.denis.testandersen.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class PlacesResponse {

    @Getter
    @SerializedName("data")
    ResponseData responseData;

    public List<Image> getImages() {
        List<Image> images = new ArrayList<>();
        List<ChildData> childrenData = responseData.getChildData();

        for (ChildData childData : childrenData) {
            PlaceData placeData = childData.getPlaceData();
            if (placeData.isNeedToInclude()) {
                images.addAll(placeData.getPreview().getImages());
            }
        }
        return images;
    }
}
