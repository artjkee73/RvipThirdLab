package site_structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {

    private final List<Image> listImages ;

    public Page(){
        listImages = addImages();
    }
    public List<Image> getListImages() {
        return listImages;
    }

    private List<Image> addImages(){
        int numberImages = 1;
        List image = new ArrayList<>();
        for (int i = 0;i < numberImages ; i++){
            image.add(new Image());
        }
        return image;
    }

}
