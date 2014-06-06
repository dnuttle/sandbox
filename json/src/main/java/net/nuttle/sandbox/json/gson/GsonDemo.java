/*************************************************************************
 *
 * ADOBE CONFIDENTIAL
 * __________________
 *
 *  Copyright 2012 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/
package net.nuttle.sandbox.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 */
public class GsonDemo {
 
  
  public static void gsonDemo() {
    Albums albums = new Albums();
    albums.title = "Free Music Archive";
    albums.message = "";
    albums.total = "11259";
    albums.page = 1;
    albums.limit="5";
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String json = gson.toJson(albums);
    System.out.println(json);
    Albums albums2 = gson.fromJson(json, Albums.class);
    System.out.println("Do instances match: " + albums2.title.equals(albums.title));
  }
  public static class Albums {
    public String title;
    public String message;
    public String[] errors = new String[]{};
    public String total;
    public int total_pages;
    public int page;
    public String limit;
  }

}
