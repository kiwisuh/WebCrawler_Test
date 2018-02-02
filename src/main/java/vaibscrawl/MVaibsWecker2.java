package vaibscrawl;

import java.util.logging.LogManager;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Vaibs
 *
 */

public class MVaibsWecker2 extends WebCrawler {
    // final static Logger logger = Logger.getLogger(MVaibsWecker.class);
    private final static Pattern FILTERS = Pattern
	    .compile(".*(\\.(css|js|gif|jpe?g" + "|png|mp3|mp3|zip|gz))$");
    String urli = "http://google.com/";

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
    @Override
    public void visit(Page page) {
	String href = page.getWebURL().getURL();
	// System.out.println("URL: " + url);
	LogManager.getLogManager().reset();

	System.out.println("      " + href);
	// do whatever with the url

	// YOU CAN GET THE CONTENT OF THE URL AND ADD SOME REJEX TO FILTER OUT
	// EMAIL,PHONE NO,NAMES, SOME SPECIFIC INFO.
    }

}
