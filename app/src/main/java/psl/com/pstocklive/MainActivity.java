package psl.com.pstocklive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import psl.com.pstocklive.alphavantage.AlphaVantageService;
import psl.com.pstocklive.alphavantage.RetrofitAlphaVantageServiceFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.hello_world_salute)
    TextView helloText;

    @BindView(R.id.stock_updates_recycler_view)
    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private StockDataAdapter stockDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        stockDataAdapter = new StockDataAdapter();
        recyclerView.setAdapter(stockDataAdapter);

        Observable.just("Please use this app responsibly!")
                .subscribe(s -> helloText.setText(s));

        AlphaVantageService avService = new RetrofitAlphaVantageServiceFactory().create();

        String[] symbols = {"BGNE", "UCTT", "VIPS", "AMD", "NVDA", "SNAP", "NFLX", "MSFT", "AMZN", "GOOG", "AAPL"};

        AtomicLong index = new AtomicLong(0);
        Observable.interval(0, 3, TimeUnit.SECONDS)
                .doOnNext(i -> {
                    index.lazySet(i + 1);
                    Log.d("APP", "new interval " + i);
                })
                .switchMap(i -> Observable.fromArray(symbols).doOnDispose(() -> helloText.setText("Refreshing..." + index)).unsubscribeOn(AndroidSchedulers.mainThread()))
                .concatMap(s -> avService.intradayQuery(s).toObservable())
                .filter(r -> r != null && r.getMetaData() != null)
                .map(r -> StockUpdate.create(r))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stockUpdate -> {
                    Log.d("APP", "New update " + stockUpdate.getStockSymbol());
                    stockDataAdapter.add(stockUpdate);
                }, e -> Log.e("APP", "onError " + e));
    }

    private void log(Throwable throwable) {
        Log.e("APP", "Error", throwable);
    }

    private void log(String stage, String item) {
        Log.d("APP", stage + ":" + Thread.currentThread().getName() + ":" + item);
    }

    private void log(String stage) {
        Log.d("APP", stage + ":" + Thread.currentThread().getName());
    }

}
