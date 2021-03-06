// INF 141 / CS 121
// Kelvin Wu
// Xueyi Fan
package ir.assignments.three;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Crawler extends WebCrawler{
	/**
	 * This method is for testing purposes only. It does not need to be used
	 * to answer any of the questions in the assignment. However, it must
	 * function as specified so that your crawler can be verified programatically.
	 * 
	 * This methods performs a crawl starting at the specified seed URL. Returns a
	 * collection containing all URLs visited during the crawl.
	 */
	private static Logger logger = LogManager.getLogger(Crawler.class);

	public static void main(String[] args) {
		Crawler.crawl("http://www.ics.uci.edu");
	}

	public static Collection<String> crawl(String seedURL) {
		System.out.println("Start");
		logger.info("test");

		/*
		 * crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */

		/*
		 * numberOfCrawlers shows the number of concurrent threads that should
		 * be initiated for crawling.
		 */
		int numberOfCrawlers = 2000;

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder("Assignment 3/crawler4jStorage");

		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(500);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
		config.setMaxDepthOfCrawling(-1);

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
		config.setMaxPagesToFetch(-1);

		/**
		 * Do you want crawler4j to crawl also binary data ?
		 * example: the contents of pdf, or the metadata of images etc
		 */
		config.setIncludeBinaryContentInCrawling(false);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 *
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);
		
		config.setUserAgentString("UCI Inf141-CS121 crawler 66476701 54535376");


		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		int index = 0;

		try {
			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);	
			FileWriter fw = new FileWriter(new File("data.txt"));
			BufferedWriter bw = new BufferedWriter(fw);
			ICSCrawler.bw = bw;
			ICSCrawler.index = index;

			/*
			 * For each crawl, you need to add some seed urls. These are the first
			 * URLs that are fetched and then the crawler starts following links
			 * which are found in these pages
			 */
			
			controller.addSeed(seedURL);
			/*
			 * Start the crawl. This is a blocking operation, meaning that your code
			 * will reach the line after this only when crawling is finished.
			 */
			controller.start(ICSCrawler.class, numberOfCrawlers);
			bw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
