package vaibscrawl;

import java.util.Date;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.util.ArrayList;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

//import Jama.Matrix;
/**
 * @author Vaibs
 * iseebug.com
 *
 */
public class Controller2 {
    public static void main(String[] args) throws Exception {
    	String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 10;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("https://sikaman.dyndns.org/");
        controller.addSeed("https://sikaman.dyndns.org/courses/");
        controller.addSeed("https://sikaman.dyndns.org/courses/4601");
    	controller.addSeed("https://carleton.ca/");
        controller.addSeed("https://github.com/");
        controller.addSeed("https://www.linkedin.com/");
        controller.addSeed("https://carleton.ca/scs/");
        controller.addSeed("https://pastebin.com/");
        controller.addSeed("https://stackoverflow.com/");
        controller.addSeed("https://pages.github.com/");
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MVaibsWecker2.class, numberOfCrawlers);
    }
}