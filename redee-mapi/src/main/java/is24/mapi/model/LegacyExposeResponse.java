package is24.mapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dprawdzik on 10.01.18.
 */
public class LegacyExposeResponse {

    public LegacyExpose getExpose() {
        return expose;
    }

    @SerializedName("expose.expose")
    public LegacyExpose expose;


}
