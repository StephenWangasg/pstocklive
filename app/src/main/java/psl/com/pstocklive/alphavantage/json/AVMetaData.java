package psl.com.pstocklive.alphavantage.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aiq on 3/14/18.
 */

public class AVMetaData {
    @SerializedName("1. Information")
    private String mInfo;

    @SerializedName("2. Symbol")
    private String mSymbol;

    @SerializedName("3. Last Refreshed")
    private String mLastRefreshed;

    @SerializedName("4. Interval")
    private String mInterval;

    @SerializedName("5. Output Size")
    private String mSize;

    @SerializedName("6. Time Zone")
    private String mTimeZone;

    public String getInfo() {
        return mInfo;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getLastRefreshed() {
        return mLastRefreshed;
    }

    public String getInterval() {
        return mInterval;
    }

    public String getOutputSize() {
        return mSize;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

}
