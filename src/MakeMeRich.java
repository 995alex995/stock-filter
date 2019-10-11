import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ",
			"IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE", "AMZN", "CRAY", "CSCO",
			"SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN",
			"YHOO");

	public static void setCopyright() {
		try {
			for (File f : FileUtils.listFiles(new File("src"), new String[] { "java" }, true))
					FileUtils.write(f, "\n\n// Copyright (c) alex#59", "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String API_KEY = "KYI56CZCERWQX1DB";
	public static String URLBase = "https://www.alphavantage.co/query";
	
	public static void main(String[] args) throws IOException {

		getData();
		
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
	System.out.println("The highest priced stock under 500 dollars is " +HP); }
	
	public static void getData() throws IOException {

		String URL = URLBase + "?function=BATCH_STOCK_QUOTES";
		URL += "&symbols=";
		URL += "&apikey=" + API_KEY;
				
		for (int i = 0; i < symbols.size(); i++) {
			if (i == symbols.size() - 1) {
				URL += symbols.get(i);
			} else {
				URL += symbols.get(i) + ",";
			}
		}

		URL request = new URL(URL);
		InputStream Stream = request.openStream();
		String response = IOUtils.toString(Stream);

		JSONObject base = new JSONObject(response);
		JSONArray stockQuotes = (JSONArray) base.get("Stock Quotes");
		
		for (int i = 0; i < stockQuotes.length(); i++) {
			JSONObject Object = (JSONObject) stockQuotes.get(i);
			StockInfo info = new StockInfo(Object.getString("1. symbol"), Object.getDouble("2. price"));
			StockUtil.Stock(info);
		}

	}

	
}