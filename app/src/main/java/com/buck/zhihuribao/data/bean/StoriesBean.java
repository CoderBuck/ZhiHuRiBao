package com.buck.zhihuribao.data.bean;

import java.util.List;

public class StoriesBean {
    /**
     * images : ["http://pic3.zhimg.com/827850266444dfd515b955f87aef029e.jpg"]
     * type : 0
     * multipic : true   多图
     * id : 8998817
     * ga_prefix : 112119
     * title : 认识一个更加真实和好玩儿的非洲，从乌干达开始
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private boolean multipic;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}