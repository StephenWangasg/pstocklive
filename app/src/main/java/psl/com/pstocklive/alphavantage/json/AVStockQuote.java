package psl.com.pstocklive.alphavantage.json;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by aiq on 3/14/18.
 */

public class AVStockQuote {

    @SerializedName("1. open")
    private BigDecimal open;

    @SerializedName("2. high")
    private BigDecimal high;

    @SerializedName("3. low")
    private BigDecimal low;

    @SerializedName("4. close")
    private BigDecimal close;

    @SerializedName("5. volume")
    private String volume;

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public String getVolume() {
        return volume;
    }

}
