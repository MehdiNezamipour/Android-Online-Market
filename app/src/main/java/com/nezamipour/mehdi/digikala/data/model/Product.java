package com.nezamipour.mehdi.digikala.data.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable {

	@Expose
	@SerializedName("menu_order")
	private int menu_order;
	@Expose
	@SerializedName("grouped_products")
	private List<String> grouped_products;
	@Expose
	@SerializedName("variations")
	private List<String> variations;

	@Expose
	@SerializedName("attributes")
	private List<Attribute> attributes;
	@Expose
	@SerializedName("images")
	private List<Images> images;

	@Expose
	@SerializedName("categories")
	private List<Categories> categories;
	@Expose
	@SerializedName("purchase_note")
	private String purchase_note;
	@Expose
	@SerializedName("parent_id")
	private int parent_id;

	@Expose
	@SerializedName("external_url")
	private String external_url;

	@Expose
	@SerializedName("total_sales")
	private int total_sales;
	@Expose
	@SerializedName("purchasable")
	private boolean purchasable;
	@Expose
	@SerializedName("on_sale")
	private boolean on_sale;
	@Expose
	@SerializedName("price_html")
	private String price_html;
	@Expose
	@SerializedName("sale_price")
	private String sale_price;
	@Expose
	@SerializedName("regular_price")
	private String regular_price;
	@Expose
	@SerializedName("price")
	private String price;
	@Expose
	@SerializedName("short_description")
	private String short_description;
	@Expose
	@SerializedName("description")
	private String description;
	@Expose
	@SerializedName("featured")
	private boolean featured;
	@Expose
	@SerializedName("status")
	private String status;
	private String permalink;
	@Expose
	@SerializedName("slug")
	private String slug;
	@Expose
	@SerializedName("name")
	private String name;
	@Expose
	@SerializedName("id")
	private int id;


	public int getMenu_order() {
		return menu_order;
	}

	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}

	public List<String> getGrouped_products() {
		return grouped_products;
	}

	public void setGrouped_products(List<String> grouped_products) {
		this.grouped_products = grouped_products;
	}

	public List<String> getVariations() {
		return variations;
	}

	public void setVariations(List<String> variations) {
		this.variations = variations;
	}


	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}


	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public String getPurchase_note() {
		return purchase_note;
	}

	public void setPurchase_note(String purchase_note) {
		this.purchase_note = purchase_note;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}


	public int getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}

	public boolean getPurchasable() {
		return purchasable;
	}

	public void setPurchasable(boolean purchasable) {
		this.purchasable = purchasable;
	}

	public boolean getOn_sale() {
		return on_sale;
	}

	public void setOn_sale(boolean on_sale) {
		this.on_sale = on_sale;
	}

	public String getPrice_html() {
		return price_html;
	}

	public void setPrice_html(String price_html) {
		this.price_html = price_html;
	}

	public String getSale_price() {
		return sale_price;
	}

	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}

	public String getRegular_price() {
		return regular_price;
	}

	public void setRegular_price(String regular_price) {
		this.regular_price = regular_price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean getFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
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


	public static class Images {
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

	public static class Categories {
		@Expose
		@SerializedName("slug")
		private String slug;
		@Expose
		@SerializedName("name")
		private String name;
		@Expose
		@SerializedName("id")
		private int id;

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
	}


}