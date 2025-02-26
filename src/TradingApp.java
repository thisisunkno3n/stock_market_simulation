public class TradingApp {
    public static void main(String[] args) {
        // Create a user (for demonstration purposes)
        User user = new User("TraderJoe", 10000.0);
        System.out.println(user);
        
        // Create a StockMarket instance and load the trade history from "History.txt"
        StockMarket market = new StockMarket();
        market.loadTradeHistory("History.txt");
        
        // Optionally print the raw trade history
        market.printTradeHistory();
        
        // Process trades into a priority queue (based on spread)
        market.processTrades();
        
        // Print trades ordered by priority (spread, highest first)
        market.printTradesByPriority();
        
        // Add further functionality (such as placing orders, simulating trades, etc.)
    }
}
 