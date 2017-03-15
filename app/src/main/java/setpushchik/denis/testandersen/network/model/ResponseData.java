package setpushchik.denis.testandersen.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public class ResponseData {

    @Getter
    @SerializedName("children")
    private List<ChildData> childData;
}
