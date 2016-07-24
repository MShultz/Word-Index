
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		String fileName = "WordIndex.bin";
		final int indexSize = 25;

		WordIndex.initialize(fileName, indexSize);

		WordIndex wi = new WordIndex(fileName);
		wi.add("Hello", "www.yes.com");
		wi.add("Hello", "www.no.com");
		wi.add("Hello", "www.maybe.com");
	//	wi.add("Goodbye", "www.one.com");
	//	wi.add("Goodbye", "www.two.com");

		Iterator<UrlEntry> helloUrls = wi.getUrls("Hello");
		ArrayList<String> urls = new ArrayList<String>();
		do {
			urls.add(helloUrls.next().getUrl());
		} while (helloUrls.hasNext());

		for (String s : urls) {
			System.out.println(s);
		}

//		Iterator<UrlEntry> goodbyeUrls = wi.getUrls("Goodbye");
//		ArrayList<String> urls2 = new ArrayList<String>();
//		do {
//			urls2.add(goodbyeUrls.next().getUrl());
//		} while (goodbyeUrls.hasNext());
//
//		for (String s : urls2) {
//			System.out.println(s);
//		}

	}
}
