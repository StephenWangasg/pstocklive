package psl.com.pstocklive.alphavantage.json;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class AVStockResult {

    @SerializedName("Meta Data")
    private AVMetaData mMetadata;

    @SerializedName("Time Series (1min)")
    private Map<String, AVStockQuote> mQuotes;

    public AVMetaData getMetaData() {
        return mMetadata;
    }

    public Map<String, AVStockQuote> getQuotes() {
        return mQuotes;
    }

    public AVStockQuote getLatest() {
        for (Map.Entry<String, AVStockQuote> kv : mQuotes.entrySet()) {
            return kv.getValue();
        }
        return null;
    }

}
