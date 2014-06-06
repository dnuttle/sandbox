package net.nuttle.sandbox.httpclient;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

public class HttpClientLab 
{
	private static final Logger LOG = Logger.getLogger(HttpClientLab.class);

	/**
	 * Makes call to localhost, assumes that content received is a simple string.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void getExample1() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost");
		CloseableHttpResponse response = client.execute(get);
		try {
			HttpEntity entity = response.getEntity();
			if (entity!=null) {
				/* This would also work, if you need output in a writer
				IOUtils.copy(entity.getContent(), w, "UTF-8");
				LOG.debug(w.toString());
				//Also, httpcore has EntityUtils which has a toString method like IOUtils
				*/
				LOG.debug(IOUtils.toString(entity.getContent(), "UTF-8"));
			}
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
		
	}
}
