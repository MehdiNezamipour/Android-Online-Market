
package com.nezamipour.mehdi.digikala.data.model.product;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collection implements Serializable
{

    @SerializedName("href")
    @Expose
    private String href;
    private final static long serialVersionUID = 3989114649537314577L;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
