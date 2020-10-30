package com.nezamipour.mehdi.digikala.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attribute {
    @Expose
    @SerializedName("options")
    private List<String> options;
    @Expose
    @SerializedName("variation")
    private boolean variation;
    @Expose
    @SerializedName("visible")
    private boolean visible;
    @Expose
    @SerializedName("position")
    private int position;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("slug")
    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Expose
    @SerializedName("id")
    private int id;

    private List<Term> terms;

    public class Term {

        private String id;
        private String name;
        private String slug;
        private String AttributeSlug;
        private int count;

        public String getAttributeSlug() {
            return AttributeSlug;
        }

        public void setAttributeSlug(String attributeSlug) {
            AttributeSlug = attributeSlug;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSlug() {
            return slug;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public boolean getVariation() {
        return variation;
    }

    public void setVariation(boolean variation) {
        this.variation = variation;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
