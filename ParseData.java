/**
 * this class stores title, description and,  text of the body, outgoing url
 */
import java.util.*;
public class ParseData {
    private String title;
    private String description;
    private String text;
    private List<String>url = new ArrayList<String>();

    /**
     * @return list of string type of url
     */
    public List<String> getUrl() {
        return url;
    }
    /**
     * @return the description of the website as a String
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return the body text of the website os a string
     */
    public String getText() {
        return text;
    }
    /**
     * @return the title of a website as string
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param description: a string description in header of html
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param text: body text of the html
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * @param title: title of the website
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @param url: a list type of url in body of the website
     */
    public void setUrl(List<String> url) {
        this.url = url;
    }
}
