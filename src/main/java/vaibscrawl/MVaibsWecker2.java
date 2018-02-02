package vaibscrawl;

import java.util.Set;
import java.util.logging.LogManager;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.mongodb.*;
import org.bson.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vaibs
 *
 */
public class MVaibsWecker2 extends WebCrawler {
    // final static Logger logger = Logger.getLogger(MVaibsWecker.class);
	static int counter = 0;
    private final static Pattern FILTERS = Pattern
	    .compile(".*(\\.(css|js|gif|jpe?g" + "|png|mp3|mp3|zip|gz))$");
    String urli = "https://sikaman.dyndns.org/";

    /**
     * This method receives two parameters. The first parameter is the page in
     * which we have discovered this new url and the second parameter is the new
     * url. You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic). In this example,
     * we are instructing the crawler to ignore urls that have css, js, git, ...
     * extensions and to only accept urls that start with
     * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
     * parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
	String href = url.getURL().toLowerCase();

	return !FILTERS.matcher(href).matches();
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    MongoClient client = new MongoClient("localhost", 27017);
	@SuppressWarnings("deprecation")
	DB database = client.getDB("WebCrawler");
	DBCollection coll_temp = database.getCollection("Urls");
    @Override
    public void visit(Page page){
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);
		BasicDBObject document = new BasicDBObject();
		document.put("url_id", counter);
		document.put("url_Name", url);
		coll_temp.insert(document);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
            System.out.println("Number of visited pages: " + counter);
            counter++;
        }
   }

}
