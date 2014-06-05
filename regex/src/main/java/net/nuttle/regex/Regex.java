package net.nuttle.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Regex {

  public void testForWhitespace(String s) {
    Pattern p = Pattern.compile("^.*\\s");
    Matcher m = p.matcher(s);
    System.out.println("Found whitespace: " + m.find());
  }
}
