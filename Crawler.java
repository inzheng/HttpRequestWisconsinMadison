import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URISyntaxException;


public class Crawler {
    /**
     * this method requests a html form through url and get response from the server.
     * @param url: a url string
     * @return htmlForm : a string which includes the html form
     */
    public static String getHtml(String url) throws IOException {

        // declare the necessary variable
        String htmlForm = "";
        String webUrl = url;
        String input;
        BufferedReader in;
        StringBuffer stringBuffer = new StringBuffer();

        // 1. Create a HTTP request
        URL url1 = new URL(webUrl); // URL url1 = new URL(url); why it is not work
        HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection(); // create a HttpsURLConnection object
        // 2. Send request and get reponse
        connection.setRequestMethod("GET");
        int response = connection.getResponseCode();
        // 3. Get html from the response
        if(response == 200) {   //if the request is successful read the response
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((input = in.readLine()) != null)
                stringBuffer.append(input);
            in.close();
            connection.disconnect();
            htmlForm = stringBuffer.toString();
        }else {                 //if the request is not successful read the error
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while((input = in.readLine()) != null) {
                stringBuffer.append(input);
            }
            htmlForm = stringBuffer.toString();
        }
        return htmlForm;
    }


    public static void main(String[] args) throws MalformedURLException {
       Crawler crawler = new Crawler();
       String html = "";
       try{
           html = crawler.getHtml("https://www.wisc.edu/");
       } catch (IOException e) {
           System.out.println("There is a error");
       }
       System.out.print(html);      // print the output
    }


}
