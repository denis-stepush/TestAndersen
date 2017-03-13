package setpushchik.denis.testandersen.network.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class PlaceData {

    private static final String IMAGE = "image";

    @Getter
    private PreviewDate preview;
    @SerializedName("post_hint")
    private String postHint;

    public boolean isNeedToInclude(){
        return postHint.equals(IMAGE);
    }
}
