package ru.netology;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;

public class Request {

    public static MultiMap getQueryParam(String name) {
        MultiMap parameters = new MultiValueMap();
        List<NameValuePair> params;
        try {
            params = URLEncodedUtils.parse(new URI(name), Charset.forName("UTF-8"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        for (NameValuePair param : params) {
            parameters.put(param.getName(), param.getValue());
        }
        return parameters;
    }


    public static String getPathWithoutParam(String url) {
        String result;
        int i = url.indexOf("?");
        if (i == -1) {
            return url;
        }
        result = url.substring(0, i);
        return result;
    }
}
