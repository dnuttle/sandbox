package net.nuttle.sandbox.jsoup;

/**
 *
 */
public class Health {

  private String host;
  private String hdfs;
  private String jobTracker;
  
  public void setHost(String host) {
    this.host = host;
  }
  
  public String getHost() {
    return host;
  }

  /**
   * @return the hdfs
   */
  public String getHdfs() {
    return hdfs;
  }

  /**
   * @param hdfs the hdfs to set
   */
  public void setHdfs(String hdfs) {
    this.hdfs = hdfs;
  }

  /**
   * @return the jobTracker
   */
  public String getJobTracker() {
    return jobTracker;
  }

  /**
   * @param jobTracker the jobTracker to set
   */
  public void setJobTracker(String jobTracker) {
    this.jobTracker = jobTracker;
  }
}
