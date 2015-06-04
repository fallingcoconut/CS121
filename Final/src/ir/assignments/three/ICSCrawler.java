// INF 141 / CS 121
// Kelvin Wu
// Xueyi Fan
package ir.assignments.three;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import org.apache.http.Header;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ICSCrawler extends WebCrawler
{
	private static Logger logger = LogManager.getLogger(ICSCrawler.class);

	private final static Pattern BINARY_FILES_EXTENSIONS =
			Pattern.compile(".*\\.(bmp|gif|jpe?g|png|tiff?|pdf|ico|xaml|pict|rif|pptx?|ps" +
					"|mid|mp2|mp3|mp4|wav|wma|au|aiff|flac|ogg|3gp|aac|amr|au|vox" +
					"|avi|mov|mpe?g|ra?m|m4v|smil|wm?v|swf|aaf|asf|flv|mkv" +
					"|zip|rar|gz|7z|aac|ace|alz|apk|arc|arj|dmg|jar|lzip|lha)" +
					"(\\?.*)?$"); // For url Query parts ( URL?q=... )
	public static BufferedWriter bw;
		
	@Override
	public boolean shouldVisit(WebURL url) {
		// TODO Auto-generated method stub
		String href = url.getURL().toLowerCase();

		return !BINARY_FILES_EXTENSIONS.matcher(href).matches() && href.contains("ics.uci.edu");
		//startsWith("http://www.ics.uci.edu/");
	}
	
	public static int index;

	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();
		String anchor = page.getWebURL().getAnchor();

		
		logger.info(String.format("Docid: %s", docid));
		logger.info(String.format("URL: ", url));
		logger.info(String.format("Domain: '%s'", domain));
		logger.info(String.format("Sub-domain: '%s'", subDomain));
		logger.info(String.format("Path: '%s'", path));
		logger.info(String.format("Parent page: %s", parentUrl));
		logger.info(String.format("Anchor text: %s", anchor));
		

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

			logger.info(String.format("Text length: %s", text.length()));
			logger.info(String.format("Html length: %s", html.length()));
			logger.info(String.format("Number of outgoing links: %s", links.size()));
			
			index++; // update the page index number
			try {
				// write the subdomain, the url, and the page content to the database (data.txt)
				bw.write(subDomain.replace("www.", "") + "\n" + url + "\n" + text + "\n" + "ENDOFPAGE "+ index + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Header[] responseHeaders = page.getFetchResponseHeaders();
		if (responseHeaders != null) {
			logger.info("Response headers:");
			for (Header header : responseHeaders) {
				logger.info(String.format("\t%s: %s", header.getName(), header.getValue()));
			}
		}

		logger.info("=============");
	}
	
}
