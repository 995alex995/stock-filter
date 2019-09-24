
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ",
			"IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE", "AMZN", "CRAY", "CSCO",
			"SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN",
			"YHOO");

	public static void main(String[] args) {

		// 1. Print these symbols using a Java 8 for-each and lambdas
		symbols.stream().forEach((symbol1) -> System.out.print(symbol1 + ", "));
		// 2. Use the StockUtil class to print the price of Bitcoin
		symbols.stream().forEach(symbol1 -> System.out.println(StockUtil.getPrice(symbol1)));
		// 3. Create a new List of StockInfo that includes the stock price
		List<StockInfo> list = new ArrayList<StockInfo>();
		for (String string : symbols) {
			list.add(StockUtil.getPrice(string));}
		System.out.println(list);
	// 4. Find the highest-priced stock under $500
	StockInfo HP = list.stream().filter(StockUtil.isPriceLessThan(500)).reduce(StockUtil::pickHigh).get();
	System.out.println("The highest priced stock under 500 dollars is " +HP);
	}

}
