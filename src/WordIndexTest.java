import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;

public class WordIndexTest {

	@Test
	public void test() {
		String fileName = "WordIndex.bin";
		final int indexSize = 25;

		assertFalse(new File(fileName).exists());
		WordIndex.initialize(fileName, indexSize);
		assertTrue(new File(fileName).exists());

		WordIndex wi = new WordIndex(fileName);
		wi.add("Hello", "www.yes.com");

		Iterator<UrlEntry> gottenUrls = wi.getUrls("Hello");
		assertEquals("www.yes.com", gottenUrls.next().getUrl());
		assertFalse(gottenUrls.hasNext());

		wi.add("Hello", "www.no.com");
		wi.add("Hello", "www.maybe.com");
		wi.add("Goodbye", "www.one.com");
		wi.add("Goodbye", "www.two.com");

		Iterator<UrlEntry> helloUrls = wi.getUrls("Hello");
		ArrayList<String> urls = new ArrayList<String>();
		do {
			urls.add(helloUrls.next().getUrl());
		} while (helloUrls.hasNext());

		assertTrue(urls.contains("www.yes.com"));
		assertTrue(urls.contains("www.no.com"));
		assertTrue(urls.contains("www.maybe.com"));
		assertFalse(urls.contains("www.one.com"));
		assertFalse(urls.contains("www.two.com"));

		wi.add("Hello", "www.no.com");
		Iterator<UrlEntry> helloUrls2 = wi.getUrls("Hello");
		ArrayList<UrlEntry> urls2 = new ArrayList<UrlEntry>();
		do {
			urls2.add(helloUrls2.next());
		} while (helloUrls2.hasNext());
		for(UrlEntry ue: urls2){
			if(ue.getUrl().equals("www.no.com")){
				assertEquals(2, ue.getCount());
			}
		}
		
		wi.close();
		WordIndex.delete(fileName);
		assertFalse(new File(fileName).exists());
	}

}
