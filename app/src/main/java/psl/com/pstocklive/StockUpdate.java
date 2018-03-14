package psl.com.pstocklive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import psl.com.pstocklive.alphavantage.json.AVStockResult;


public class StockUpdate implements Serializable {
    private final String stockSymbol;
    private final BigDecimal price;
    private final Date date;

    public StockUpdate(String stockSymbol, BigDecimal price, Date date) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.date = date;
    }


    public static StockUpdate create(AVStockResult r) {
        return new StockUpdate(r.getMetaData().getSymbol(), r.getLatest().getClose(), new Date());
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
