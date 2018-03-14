package psl.com.pstocklive.alphavantage;

import io.reactivex.Single;
import psl.com.pstocklive.alphavantage.json.AVStockResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlphaVantageService {

    @GET("query?function=TIME_SERIES_INTRADAY&interval=1min&apikey=C6OFZSHA8YTJYXY9")
    Single<AVStockResult> intradayQuery(@Query("symbol") String symbol);
}
