package com.thx.insight.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.freaknet.gtrends.api.GoogleAuthenticator;
import org.freaknet.gtrends.api.GoogleTrendsClient;
import org.freaknet.gtrends.api.GoogleTrendsCsvParser;
import org.freaknet.gtrends.api.GoogleTrendsRequest;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsClientException;
import org.freaknet.gtrends.api.exceptions.GoogleTrendsRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thx.insight.dao.BaseDao;
import com.thx.insight.entity.GoogleTrends;

@Service
@Transactional
public class GoogleTrendsService extends BaseService<GoogleTrends, Long>{

	@Override
	protected BaseDao<GoogleTrends, Long> getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		try {
			new GoogleTrendsService().getGoogleTrendsByKey("docker");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GoogleTrendsRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GoogleTrendsClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getGoogleTrendsByKey(String key) throws GoogleTrendsRequestException, ConfigurationException, GoogleTrendsClientException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		String u = "it.huanghaijing@gmail.com";
        String p = "Th902521";

        /* OPTIONAL: setup a proxy with NTLM authentication */
        HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");
        Credentials credentials = new NTCredentials("", "", "", "");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxy.getHostName(), proxy.getPort()), credentials);
        
//        org.apache.http.ssl.SSLContextBuilder context_b = SSLContextBuilder.create();
//        context_b.loadTrustMaterial(new org.apache.http.conn.ssl.TrustSelfSignedStrategy());
//        SSLContext ssl_context = context_b.build();
//        org.apache.http.conn.ssl.SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(ssl_context,
//                new org.apache.http.conn.ssl.DefaultHostnameVerifier());
//
//        HttpClientBuilder builder = HttpClients.custom()
//            .setSSLSocketFactory(sslSocketFactory);
//        CloseableHttpClient httpClient = builder.build();
        
//        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        /* Creates a new authenticator */
        GoogleAuthenticator authenticator = new GoogleAuthenticator(u, p, (DefaultHttpClient) httpClient);

        /* Creates a new Google Trends Client */
        GoogleTrendsClient client = new GoogleTrendsClient(authenticator, (DefaultHttpClient) httpClient);
        GoogleTrendsRequest request = new GoogleTrendsRequest("bananas");

        /* Here the default request params can be modified with getter/setter methods */
        String content = client.execute(request);

        /* The default request downloads a CSV available in content */
        GoogleTrendsCsvParser csvParser = new GoogleTrendsCsvParser(content);
        /* Get a specific section of the CSV */
        String section = csvParser.getSectionAsString("Top searches for", true);
        System.out.println(section);
	}
}
