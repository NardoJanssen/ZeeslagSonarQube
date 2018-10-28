package apiConnector;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class apiConnector
{
    private static final String apiUrlBase = "http://jaspersandbox.nl:1337/api/v1";

    public Boolean checkCredentials(String username, String password)
    {
        if (pingHost(apiUrlBase))
        {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            try
            {
                HttpResponse response = httpFormRequest("/user/login", params);
                StatusLine status = response.getStatusLine();

                if (status.getStatusCode() == 200)
                {
                    return true;
                }
                else
                {
                    System.out.println(status.getStatusCode() + " " + status.getReasonPhrase());
                    return false;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }

    public void logout(String username)
    {
        if (pingHost(apiUrlBase))
        {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));

            try
            {
                // The logout method does not return anything, so we don't have to check if it worked.
                httpFormRequest("/user/logout", params);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private HttpResponse httpFormRequest(String endpoint, List<NameValuePair> formBody) throws IOException
    {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(apiUrlBase + endpoint);

        request.setEntity(new UrlEncodedFormEntity(formBody));
        return client.execute(request);
    }

    private static boolean pingHost(String host) {
        try
        {
            final URLConnection connection = new URL(host).openConnection();
            connection.connect();
            System.out.println("Service '" + host + "' available, yeah!");
            return true;
        }
        catch (final MalformedURLException e)
        {
            throw new IllegalStateException("Bad URL: " + host, e);
        }
        catch (final IOException e)
        {
            System.out.println("Service '" + host + "' unavailable, oh no!");
            return false;
        }
    }
}
