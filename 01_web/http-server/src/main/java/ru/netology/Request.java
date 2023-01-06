package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;

public class Request {
    private String method;
    private String path;
    private List<String> headers;
    private String body;

    public Request(String[] parts) {
        this.method = parts[0];
        this.path = parts[1];
        this.headers = null; // для примера
        this.body = null; // для примера
    }

    public NameValuePair getQueryParam(String name) {
        List<NameValuePair> params = getQueryParams();

        for (NameValuePair param : params
        ) {
            if (name.equals(param.getName())) return param;
        }
        return null;
    }

    public List<NameValuePair> getQueryParams() {

        List<NameValuePair> params;
        try {
            params = URLEncodedUtils.parse(new URI(path), Charset.forName("UTF-8"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return params;
    }


    public String getPath() {
        String result;
        int i = path.indexOf("?");
        if (i == -1) {
            return path;
        }
        result = path.substring(0, i);
        return result;
    }

    public String getMethod() {
        return method;
    }
}
