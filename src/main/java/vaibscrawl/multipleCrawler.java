//This is a multi threaded crawler that enables each thread that has one seed

package vaibscrawl;

import edu.uci.ics.crawler4j.crawler.CrawlConfig; 
import edu.uci.ics.crawler4j.crawler.CrawlController; 
import edu.uci.ics.crawler4j.fetcher.PageFetcher; 
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig; 
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.util.Date;



public class multipleCrawler{
	public static void main(String[] args) throws Exception {
	String crawlStorageFolder = "/data/crawl/root";
	int numberOfCrawlers = 1;
	CrawlConfig config1 = new CrawlConfig(); 
	CrawlConfig config2 = new CrawlConfig();
	
	
	PageFetcher pageFetcher1 = new PageFetcher(config1); 
	PageFetcher pageFetcher2 = new PageFetcher(config2); 

	RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); 
	RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher1);
	
	CrawlController controller1 = new CrawlController(config1, pageFetcher1, robotstxtServer); 
	CrawlController controller2 = new CrawlController(config2, pageFetcher2, robotstxtServer);
	
	controller1.addSeed("https://sikaman.dyndns.org/courses/4601");
	controller1.addSeed("https://sikaman.dyndns.org/");
	controller1.addSeed("https://sikaman.dyndns.org/courses/");
	controller2.addSeed("https://carleton.ca/");
    controller2.addSeed("https://github.com/");
    controller1.start(MVaibsWecker2.class, numberOfCrawlers);
    controller2.start(MVaibsWecker2.class, numberOfCrawlers);
	}
	 
}
