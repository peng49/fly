package fly.frontend.utils;

import fly.frontend.pojo.GiteeOauthRequest;
import fly.frontend.pojo.GiteeOauthResponse;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class HttpClientTest {

    @Test
    public void getTest() throws IOException {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.声明get请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com/s?wd=java");
        //3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.判断状态码
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
            String string = EntityUtils.toString(entity, "UTF-8");
            System.out.println(string);
        }
        //5.关闭资源
        response.close();
        httpClient.close();
    }

    @Test
    public void postTest() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("https://gitee.com/oauth/token");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");


        ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
        parameters.add(new BasicNameValuePair("code", "xxx"));
        parameters.add(new BasicNameValuePair("client_id", "xx"));
        parameters.add(new BasicNameValuePair("client_secret", "xxxx"));
        parameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8001/oauth/gitee/callback"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters, "UTF-8");

        request.setEntity(urlEncodedFormEntity);

        CloseableHttpResponse response = client.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity, "UTF-8");
            System.out.println(body);
        } else {
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }

        response.close();
        client.close();
    }

    @Test
    public void restTemplatePostTest() {
        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("code", "cccccccccccc");
        parameters.add("client_id", "vvvvvvvvvvvvv");
        parameters.add("client_secret", "xxxxxxxxxxxxxxxx");
        parameters.add("redirect_uri", "http://localhost:8001/oauth/gitee/callback");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        org.springframework.http.HttpEntity<MultiValueMap<String, String>> httpEntity = new org.springframework.http.HttpEntity<>(parameters, headers);

        List<HttpMessageConverter<?>> converters = rest.getMessageConverters();
        System.out.println(converters);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);

        rest.setMessageConverters(converters);

        ResponseEntity<GiteeOauthResponse> response = rest.postForEntity("https://gitee.com/oauth/token", httpEntity, GiteeOauthResponse.class);

        if (response.hasBody()) {
            System.out.println(response.getBody().getAccessToken());
        } else {
            System.out.println("request exception");
            System.out.println(response);
        }
    }
}
