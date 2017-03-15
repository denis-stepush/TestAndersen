package setpushchik.denis.testandersen.network.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class ChildData {

    @Getter
    @SerializedName("data")
    private PlaceData placeData;
}
