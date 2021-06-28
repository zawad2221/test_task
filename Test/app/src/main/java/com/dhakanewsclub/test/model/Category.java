package com.dhakanewsclub.test.model;


import java.util.List;

public class Category {


    private String category_id;

    private String category_name;

    public boolean isSelected;

    private List<SubCategory> subcatg = null;


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<SubCategory> getSubcatg() {
        return subcatg;
    }

    public void setSubcatg(List<SubCategory> subcatg) {
        this.subcatg = subcatg;
    }

  

}
