
public class Trade {

    private double buyPrice;
    private double sellPrice;
    private String ticker;

    public Trade(double buyPrice, double sellPrice, String ticker) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.ticker = ticker;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public String getTicker() {
        return ticker;
    }

    // Spread is used as the priority (largest spread first)
    public double getSpread() {
        return buyPrice - sellPrice;
    }

    @Override
    public String toString() {
        return "Matched Buy: " + buyPrice + " - Sell: " + sellPrice + " " + ticker;
    }
}
