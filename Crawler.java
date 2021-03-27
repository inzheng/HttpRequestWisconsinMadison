import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.*;


public class Crawler {
    //Create the ParseData object to store all information of the wisconsin madison website
    public static ParseData wisconsinData = new ParseData();

    /**
     * this method requests a html form through url and get response from the server.
     * @param url: a url string
     * @return htmlForm : The html of the valid url, otherwise null.
     */
    public static String getHtml(String url) {

        // declare the necessary variable
        String htmlForm = "";
        String webUrl = url;
        String input;
        BufferedReader in;
        StringBuffer stringBuffer = new StringBuffer();

        // 1. Create a HTTP request
        try {
            URL url1 = new URL(webUrl); // URL url1 = new URL(url); why it is not work
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection(); // create a HttpsURLConnection object
            // 2. Send request and get reponse
            connection.setRequestMethod("GET");
            int response = connection.getResponseCode();
            // 3. Get html from the response
            if (response == 200) {   //if the request is successful read the response
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((input = in.readLine()) != null)
                    stringBuffer.append(input);
                in.close();
                connection.disconnect();
                htmlForm = stringBuffer.toString();
            } else {                 //if the request is not successful read the error
                return null;
            }
        }catch(IOException e) {
            return null;
        }
        return htmlForm;
    }

    /**
     * This method is to parse the html form and store the
     * title, description, body text and all url within the body into wisconsinData variable
     * @param html: a html form
     */
    public static void Parse(String html) {
        //create a Document object to parse the html
        Document doc = Jsoup.parse(html);
        //parse the title and store in wisconsinData
        wisconsinData.setTitle(doc.title());
        //parse the description and store in wisconsinData
        wisconsinData.setDescription(doc.select("meta[name=description]").attr("content"));
        doc = Jsoup.parseBodyFragment(html);
        //parse the url in body and store them into a list
        List<String>list = new ArrayList<String>();
        for(Element element: doc.select("a[href]")) {
            if(element.parentNode().nodeName() != "header") {
                list.add(element.attr("href"));
            }
        }
        wisconsinData.setUrl(list);
        //parse the body text
        Element element = doc.body();
        String bodyText = element.ownText();
        wisconsinData.setText(bodyText);
    }

    public static void main(String[] args) throws MalformedURLException {
        Crawler crawler = new Crawler();
        String html = "";
        //http request
        html = crawler.getHtml("https://www.wisc.edu/");
        //parse all data and store them to ParseData object
        Parse(html);
        // following print is for test
//        System.out.println(wisconsinData.getText());
//        System.out.println(wisconsinData.getTitle());
//        System.out.println(wisconsinData.getUrl());
//        System.out.println(wisconsinData.getText());
    }

}
