package entities;

import java.text.DecimalFormat;

public class Sale {
    private Integer month;
    private Integer year;
    private String seller;
    private Integer items;
    private Double total;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Sale(Integer month, Integer year, String seller, Integer items, Double total) {
        this.month = month;
        this.year = year;
        this.seller = seller;
        this.items = items;
        this.total = total;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public String getSeller() {
        return seller;
    }

    public Integer getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public Double getAveragePrice() {
        return total / items;
    }

    @Override
    public String toString() {
        return month + "/" + year + ", " + seller + ", items: " + items + ", total: " + df.format(total) + ", pm = " + df.format(getAveragePrice());
    }
}
