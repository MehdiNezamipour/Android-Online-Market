package com.nezamipour.mehdi.digikala.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @Expose
    @SerializedName("_links")
    private _links _links;
    @Expose
    @SerializedName("count")
    private int count;
    @Expose
    @SerializedName("menu_order")
    private int menu_order;
    @Expose
    @SerializedName("image")
    private Image image;
    @Expose
    @SerializedName("display")
    private String display;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("parent")
    private int parent;
    @Expose
    @SerializedName("slug")
    private String slug;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public _links get_links() {
        return _links;
    }

    public void set_links(_links _links) {
        this._links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(int menu_order) {
        this.menu_order = menu_order;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public static class _links {
        @Expose
        @SerializedName("collection")
        private List<Collection> collection;
        @Expose
        @SerializedName("self")
        private List<Self> self;

        public List<Collection> getCollection() {
            return collection;
        }

        public void setCollection(List<Collection> collection) {
            this.collection = collection;
        }

        public List<Self> getSelf() {
            return self;
        }

        public void setSelf(List<Self> self) {
            this.self = self;
        }
    }

    public static class Collection {
        @Expose
        @SerializedName("href")
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Self {
        @Expose
        @SerializedName("href")
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Image {
        @Expose
        @SerializedName("alt")
        private String alt;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("src")
        private String src;
        @Expose
        @SerializedName("date_modified_gmt")
        private String date_modified_gmt;
        @Expose
        @SerializedName("date_modified")
        private String date_modified;
        @Expose
        @SerializedName("date_created_gmt")
        private String date_created_gmt;
        @Expose
        @SerializedName("date_created")
        private String date_created;
        @Expose
        @SerializedName("id")
        private int id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDate_modified_gmt() {
            return date_modified_gmt;
        }

        public void setDate_modified_gmt(String date_modified_gmt) {
            this.date_modified_gmt = date_modified_gmt;
        }

        public String getDate_modified() {
            return date_modified;
        }

        public void setDate_modified(String date_modified) {
            this.date_modified = date_modified;
        }

        public String getDate_created_gmt() {
            return date_created_gmt;
        }

        public void setDate_created_gmt(String date_created_gmt) {
            this.date_created_gmt = date_created_gmt;
        }

        public String getDate_created() {
            return date_created;
        }

        public void setDate_created(String date_created) {
            this.date_created = date_created;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}