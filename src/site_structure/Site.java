package site_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Site {

    private final List<Page> listPages;

    public Site(){
        listPages = addPages();
    }
    public List<Page> getListPages() {
        return listPages;
    }

    private List<Page> addPages(){
        int numberPages = 100;
        List pages = new ArrayList<>();
        for (int i = 0;i < numberPages ; i++){
            pages.add(new Page());
        }
        return pages;
    }
}
