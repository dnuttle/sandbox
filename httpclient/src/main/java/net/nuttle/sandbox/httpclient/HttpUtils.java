package net.nuttle.sandbox.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	/**
	 * Simple method takes a string as a URL, assumes response will be string
	 * @param url
	 * @return
	 */
	public static String doGet(String url) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			return EntityUtils.toString(response.getEntity());
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
	}
}
