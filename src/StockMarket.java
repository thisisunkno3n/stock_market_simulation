import java.io.*;
import java.util.*;

public class StockMarket {
    private LinkedListPriorityQueue<Trade> tradeQueue;
    private List<Trade> tradeHistory;
    
    public StockMarket() {
        tradeQueue = new LinkedListPriorityQueue<>();
        tradeHistory = new ArrayList<>();
    }
    
    // Loads trades from a text file.
    // The expected format per line is:
    // Matched Buy: 277.6085 - Sell: 277.60703MSFT
    public void loadTradeHistory(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Trade trade = parseTrade(line);
                if (trade != null) {
                    tradeHistory.add(trade);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading trade history: " + e.getMessage());
        }
    }
    
    // Parses a trade line from the history file.
    private Trade parseTrade(String line) {
        try {
            if (!line.startsWith("Matched Buy:")) return null;
            String withoutPrefix = line.substring("Matched Buy:".length()).trim();
            // Split on "- Sell:" to separate buy and sell parts
            String[] parts = withoutPrefix.split("- Sell:");
            if (parts.length != 2) return null;
            double buyPrice = Double.parseDouble(parts[0].trim());
            
            String sellAndTicker = parts[1].trim();
            // Extract sell price (digits and decimal point) from ticker
            int index = 0;
            while (index < sellAndTicker.length() &&
                   (Character.isDigit(sellAndTicker.charAt(index)) || sellAndTicker.charAt(index) == '.')) {
                index++;
            }
            double sellPrice = Double.parseDouble(sellAndTicker.substring(0, index));
            String ticker = sellAndTicker.substring(index).trim();
            return new Trade(buyPrice, sellPrice, ticker);
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            return null;
        }
    }
    
    // Enqueue each trade using its spread (buyPrice - sellPrice) as the priority.
    public void processTrades() {
        for (Trade trade : tradeHistory) {
            double spread = trade.getSpread();
            tradeQueue.enqueue(trade, spread);
        }
    }
    
    // Print the loaded (raw) trade history.
    public void printTradeHistory() {
        System.out.println("Trade History:");
        for (Trade trade : tradeHistory) {
            System.out.println(trade);
        }
    }
    
    // Print trades ordered by priority (spread) from highest to lowest.
    public void printTradesByPriority() {
        System.out.println("\nTrades ordered by spread (highest first):");
        while (!tradeQueue.isEmpty()) {
            Trade trade = tradeQueue.dequeue();
            System.out.println(trade);
        }
    }
}
