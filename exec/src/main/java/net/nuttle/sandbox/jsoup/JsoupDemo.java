package net.nuttle.sandbox.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Checks the health of some Corona services using Jsoup where needed to find the information in response DOM.
 * Print output to console using Velocity template.
 */
public class JsoupDemo {
  
  public static void printHealth(String host) {
    Health h = getHealth(host);
    HealthFormatter formatter = new HealthFormatter();
    System.out.println(formatter.format(h));
  }
  
  public static Health getHealth(String host) {
    Health health = new Health();
    health.setHost(host);
    health.setHdfs(isRunningHDFS(host) ? "ok" : "problem");
    health.setJobTracker(isRunningJobTracker(host) ? "ok" : "problem");
    return health;
  }
  
  public static boolean isRunningHDFS(String host) {
    try {
      StringBuilder sb = new StringBuilder();
      sb.append("http://").append(host).append(":50070/dfshealth.jsp");
      Document doc = Jsoup.connect(sb.toString()).get();
      Elements elements = doc.select("h3:contains(NameNode Storage:) + div.dfstable table tbody td:eq(2)");
      if(elements.size()==1 && "Active".equals(elements.get(0).html())) {
        return true;
      }
    } catch (Exception e) {
      //do nothing
    }
    return false;
  }
  
  public static boolean isRunningJobTracker(String host) {
    try {
      StringBuilder sb = new StringBuilder();
      sb.append("http://").append(host).append(":50030/jobtracker.jsp");
      Document doc = Jsoup.connect(sb.toString()).get();
      Elements elements = doc.select("#scheduling_info + table.sortable tbody td:eq(1)");
      if(elements.size()==1 && "running".equals(elements.get(0).html())) {
        return true;
      }
    } catch (Exception e) {
      //do nothing
    }
    return false;
  }

}
