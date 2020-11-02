
package com.nezamipour.mehdi.digikala.data.model.product;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dimensions implements Serializable
{

    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    private final static long serialVersionUID = 679931795291228355L;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}
