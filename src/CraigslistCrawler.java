import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class CraigslistCrawler {
	public static void main(String[] args) throws Exception{
		CraigslistCrawler.Crawl("https://sfbay.craigslist.org/d/apts-housing-for-rent/search/apa");
	}
	
	public static void Crawl(String url) throws Exception{ /*You need to collect following info: title/rent price/detail url/hood/ */
		System.out.println("request_url = " + url);
		Document doc = Jsoup.connect(url).get();
		System.out.println("title: " + doc.title());
		Elements items = doc.select("li[class = result-row]");
		System.out.println("num of results = " + items.size());
		
		System.out.println();
		for(Element item: items){
			System.out.println("[title]: "+ item.children().select("[class=result-title hdrlnk]").first().text());
			System.out.println("[rent price]: "+ item.children().select("[class=result-price]").first().text());
			System.out.println("[detail url]: "+ item.children().select("a[href]").attr("abs:href"));
			Element hood = item.children().select("[class=result-hood]").first();
			System.out.println("[rent hood]: "+ (hood == null? null : hood.text()));
			System.out.println();
		}
		
	}
}
