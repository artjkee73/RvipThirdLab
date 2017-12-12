package site_structure;

import java.io.Serializable;

public class Image implements Serializable {

    private String url;

    public Image(){
    url = "goo.gl/a3hCKb";
    }

    public String getUrl() {
        return url;
    }
}
