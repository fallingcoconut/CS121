package main;

import ir.assignments.three.DocScore;
import ir.assignments.three.Utilities;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	
		try {
			Utilities u = new Utilities(arg0.getServletContext().getRealPath("/stopwords.txt"), arg0.getServletContext().getRealPath("/data.txt"));
			u.loadData();
			Utilities.writeToSubdomains(arg0.getServletContext().getRealPath("/Subdomains.txt"));
			Utilities.writeToCommonWords(arg0.getServletContext().getRealPath("/CommonWords.txt"));
			System.out.println("number of unique pages: "
					+ Utilities.entries.size());
			System.out.println("number of subdomains: "
					+ Utilities.subdomainSet.size());
			System.out.println("longest page and word number: "
					+ Utilities.longestPage() + " | "
					+ Utilities.longestPageWords + " words");
			System.out.println("Number of documents: "
					+ Utilities.numberOfDocuments());
			System.out.println("Unique Words: "
					+ Utilities.numberOfUniqueWords());
			List<String> termList = Arrays.asList("hello", "world");
			List<DocScore> topdocs = Utilities.calculateDocScores(termList, 10);
			for (DocScore doc : topdocs) {
				System.out.println(doc.docId);
				System.out.println(doc.score);
			}
			arg0.getServletContext().setAttribute("u", u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
