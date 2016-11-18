package com.septianfujianto.woodroid.Model.Products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Septian A. Fujianto on 10/31/2016.
 */

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("catalog_visibility")
    @Expose
    private String catalogVisibility;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("date_on_sale_from")
    @Expose
    private String dateOnSaleFrom;
    @SerializedName("date_on_sale_to")
    @Expose
    private String dateOnSaleTo;
    @SerializedName("price_html")
    @Expose
    private String priceHtml;
    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;
    @SerializedName("purchasable")
    @Expose
    private Boolean purchasable;
    @SerializedName("total_sales")
    @Expose
    private Integer totalSales;
    @SerializedName("virtual")
    @Expose
    private Boolean virtual;
    @SerializedName("downloadable")
    @Expose
    private Boolean downloadable;
    @SerializedName("downloads")
    @Expose
    private List<Object> downloads = new ArrayList<Object>();
    @SerializedName("download_limit")
    @Expose
    private Integer downloadLimit;
    @SerializedName("download_expiry")
    @Expose
    private Integer downloadExpiry;
    @SerializedName("download_type")
    @Expose
    private String downloadType;
    @SerializedName("external_url")
    @Expose
    private String externalUrl;
    @SerializedName("button_text")
    @Expose
    private String buttonText;
    @SerializedName("tax_status")
    @Expose
    private String taxStatus;
    @SerializedName("tax_class")
    @Expose
    private String taxClass;
    @SerializedName("manage_stock")
    @Expose
    private Boolean manageStock;
    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;
    @SerializedName("in_stock")
    @Expose
    private Boolean inStock;
    @SerializedName("backorders")
    @Expose
    private String backorders;
    @SerializedName("backorders_allowed")
    @Expose
    private Boolean backordersAllowed;
    @SerializedName("backordered")
    @Expose
    private Boolean backordered;
    @SerializedName("sold_individually")
    @Expose
    private Boolean soldIndividually;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("dimensions")
    @Expose
    private Dimensions dimensions;
    @SerializedName("shipping_required")
    @Expose
    private Boolean shippingRequired;
    @SerializedName("shipping_taxable")
    @Expose
    private Boolean shippingTaxable;
    @SerializedName("shipping_class")
    @Expose
    private String shippingClass;
    @SerializedName("shipping_class_id")
    @Expose
    private Integer shippingClassId;
    @SerializedName("reviews_allowed")
    @Expose
    private Boolean reviewsAllowed;
    @SerializedName("average_rating")
    @Expose
    private String averageRating;
    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;
    @SerializedName("related_ids")
    @Expose
    private List<Integer> relatedIds = new ArrayList<Integer>();
    @SerializedName("upsell_ids")
    @Expose
    private List<Integer> upsellIds = new ArrayList<Integer>();
    @SerializedName("cross_sell_ids")
    @Expose
    private List<Integer> crossSellIds = new ArrayList<Integer>();
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("purchase_note")
    @Expose
    private String purchaseNote;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<Category>();
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = new ArrayList<Tag>();
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = new ArrayList<Object>();
    @SerializedName("default_attributes")
    @Expose
    private List<Object> defaultAttributes = new ArrayList<Object>();
    @SerializedName("variations")
    @Expose
    private List<Object> variations = new ArrayList<Object>();
    @SerializedName("grouped_products")
    @Expose
    private List<Object> groupedProducts = new ArrayList<Object>();
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     *
     * @param permalink
     * The permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     *
     * @return
     * The dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     *
     * @param dateCreated
     * The date_created
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *
     * @return
     * The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     *
     * @param dateModified
     * The date_modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The featured
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     *
     * @param featured
     * The featured
     */
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /**
     *
     * @return
     * The catalogVisibility
     */
    public String getCatalogVisibility() {
        return catalogVisibility;
    }

    /**
     *
     * @param catalogVisibility
     * The catalog_visibility
     */
    public void setCatalogVisibility(String catalogVisibility) {
        this.catalogVisibility = catalogVisibility;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     *
     * @param shortDescription
     * The short_description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     *
     * @return
     * The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     *
     * @param sku
     * The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The regularPrice
     */
    public String getRegularPrice() {
        return regularPrice;
    }

    /**
     *
     * @param regularPrice
     * The regular_price
     */
    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    /**
     *
     * @return
     * The salePrice
     */
    public String getSalePrice() {
        return salePrice;
    }

    /**
     *
     * @param salePrice
     * The sale_price
     */
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    /**
     *
     * @return
     * The dateOnSaleFrom
     */
    public String getDateOnSaleFrom() {
        return dateOnSaleFrom;
    }

    /**
     *
     * @param dateOnSaleFrom
     * The date_on_sale_from
     */
    public void setDateOnSaleFrom(String dateOnSaleFrom) {
        this.dateOnSaleFrom = dateOnSaleFrom;
    }

    /**
     *
     * @return
     * The dateOnSaleTo
     */
    public String getDateOnSaleTo() {
        return dateOnSaleTo;
    }

    /**
     *
     * @param dateOnSaleTo
     * The date_on_sale_to
     */
    public void setDateOnSaleTo(String dateOnSaleTo) {
        this.dateOnSaleTo = dateOnSaleTo;
    }

    /**
     *
     * @return
     * The priceHtml
     */
    public String getPriceHtml() {
        return priceHtml;
    }

    /**
     *
     * @param priceHtml
     * The price_html
     */
    public void setPriceHtml(String priceHtml) {
        this.priceHtml = priceHtml;
    }

    /**
     *
     * @return
     * The onSale
     */
    public Boolean getOnSale() {
        return onSale;
    }

    /**
     *
     * @param onSale
     * The on_sale
     */
    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    /**
     *
     * @return
     * The purchasable
     */
    public Boolean getPurchasable() {
        return purchasable;
    }

    /**
     *
     * @param purchasable
     * The purchasable
     */
    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    /**
     *
     * @return
     * The totalSales
     */
    public Integer getTotalSales() {
        return totalSales;
    }

    /**
     *
     * @param totalSales
     * The total_sales
     */
    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    /**
     *
     * @return
     * The virtual
     */
    public Boolean getVirtual() {
        return virtual;
    }

    /**
     *
     * @param virtual
     * The virtual
     */
    public void setVirtual(Boolean virtual) {
        this.virtual = virtual;
    }

    /**
     *
     * @return
     * The downloadable
     */
    public Boolean getDownloadable() {
        return downloadable;
    }

    /**
     *
     * @param downloadable
     * The downloadable
     */
    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
    }

    /**
     *
     * @return
     * The downloads
     */
    public List<Object> getDownloads() {
        return downloads;
    }

    /**
     *
     * @param downloads
     * The downloads
     */
    public void setDownloads(List<Object> downloads) {
        this.downloads = downloads;
    }

    /**
     *
     * @return
     * The downloadLimit
     */
    public Integer getDownloadLimit() {
        return downloadLimit;
    }

    /**
     *
     * @param downloadLimit
     * The download_limit
     */
    public void setDownloadLimit(Integer downloadLimit) {
        this.downloadLimit = downloadLimit;
    }

    /**
     *
     * @return
     * The downloadExpiry
     */
    public Integer getDownloadExpiry() {
        return downloadExpiry;
    }

    /**
     *
     * @param downloadExpiry
     * The download_expiry
     */
    public void setDownloadExpiry(Integer downloadExpiry) {
        this.downloadExpiry = downloadExpiry;
    }

    /**
     *
     * @return
     * The downloadType
     */
    public String getDownloadType() {
        return downloadType;
    }

    /**
     *
     * @param downloadType
     * The download_type
     */
    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    /**
     *
     * @return
     * The externalUrl
     */
    public String getExternalUrl() {
        return externalUrl;
    }

    /**
     *
     * @param externalUrl
     * The external_url
     */
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    /**
     *
     * @return
     * The buttonText
     */
    public String getButtonText() {
        return buttonText;
    }

    /**
     *
     * @param buttonText
     * The button_text
     */
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    /**
     *
     * @return
     * The taxStatus
     */
    public String getTaxStatus() {
        return taxStatus;
    }

    /**
     *
     * @param taxStatus
     * The tax_status
     */
    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    /**
     *
     * @return
     * The taxClass
     */
    public String getTaxClass() {
        return taxClass;
    }

    /**
     *
     * @param taxClass
     * The tax_class
     */
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    /**
     *
     * @return
     * The manageStock
     */
    public Boolean getManageStock() {
        return manageStock;
    }

    /**
     *
     * @param manageStock
     * The manage_stock
     */
    public void setManageStock(Boolean manageStock) {
        this.manageStock = manageStock;
    }

    /**
     *
     * @return
     * The stockQuantity
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     *
     * @param stockQuantity
     * The stock_quantity
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     *
     * @return
     * The inStock
     */
    public Boolean getInStock() {
        return inStock;
    }

    /**
     *
     * @param inStock
     * The in_stock
     */
    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    /**
     *
     * @return
     * The backorders
     */
    public String getBackorders() {
        return backorders;
    }

    /**
     *
     * @param backorders
     * The backorders
     */
    public void setBackorders(String backorders) {
        this.backorders = backorders;
    }

    /**
     *
     * @return
     * The backordersAllowed
     */
    public Boolean getBackordersAllowed() {
        return backordersAllowed;
    }

    /**
     *
     * @param backordersAllowed
     * The backorders_allowed
     */
    public void setBackordersAllowed(Boolean backordersAllowed) {
        this.backordersAllowed = backordersAllowed;
    }

    /**
     *
     * @return
     * The backordered
     */
    public Boolean getBackordered() {
        return backordered;
    }

    /**
     *
     * @param backordered
     * The backordered
     */
    public void setBackordered(Boolean backordered) {
        this.backordered = backordered;
    }

    /**
     *
     * @return
     * The soldIndividually
     */
    public Boolean getSoldIndividually() {
        return soldIndividually;
    }

    /**
     *
     * @param soldIndividually
     * The sold_individually
     */
    public void setSoldIndividually(Boolean soldIndividually) {
        this.soldIndividually = soldIndividually;
    }

    /**
     *
     * @return
     * The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     * The dimensions
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     *
     * @param dimensions
     * The dimensions
     */
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     *
     * @return
     * The shippingRequired
     */
    public Boolean getShippingRequired() {
        return shippingRequired;
    }

    /**
     *
     * @param shippingRequired
     * The shipping_required
     */
    public void setShippingRequired(Boolean shippingRequired) {
        this.shippingRequired = shippingRequired;
    }

    /**
     *
     * @return
     * The shippingTaxable
     */
    public Boolean getShippingTaxable() {
        return shippingTaxable;
    }

    /**
     *
     * @param shippingTaxable
     * The shipping_taxable
     */
    public void setShippingTaxable(Boolean shippingTaxable) {
        this.shippingTaxable = shippingTaxable;
    }

    /**
     *
     * @return
     * The shippingClass
     */
    public String getShippingClass() {
        return shippingClass;
    }

    /**
     *
     * @param shippingClass
     * The shipping_class
     */
    public void setShippingClass(String shippingClass) {
        this.shippingClass = shippingClass;
    }

    /**
     *
     * @return
     * The shippingClassId
     */
    public Integer getShippingClassId() {
        return shippingClassId;
    }

    /**
     *
     * @param shippingClassId
     * The shipping_class_id
     */
    public void setShippingClassId(Integer shippingClassId) {
        this.shippingClassId = shippingClassId;
    }

    /**
     *
     * @return
     * The reviewsAllowed
     */
    public Boolean getReviewsAllowed() {
        return reviewsAllowed;
    }

    /**
     *
     * @param reviewsAllowed
     * The reviews_allowed
     */
    public void setReviewsAllowed(Boolean reviewsAllowed) {
        this.reviewsAllowed = reviewsAllowed;
    }

    /**
     *
     * @return
     * The averageRating
     */
    public String getAverageRating() {
        return averageRating;
    }

    /**
     *
     * @param averageRating
     * The average_rating
     */
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    /**
     *
     * @return
     * The ratingCount
     */
    public Integer getRatingCount() {
        return ratingCount;
    }

    /**
     *
     * @param ratingCount
     * The rating_count
     */
    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    /**
     *
     * @return
     * The relatedIds
     */
    public List<Integer> getRelatedIds() {
        return relatedIds;
    }

    /**
     *
     * @param relatedIds
     * The related_ids
     */
    public void setRelatedIds(List<Integer> relatedIds) {
        this.relatedIds = relatedIds;
    }

    /**
     *
     * @return
     * The upsellIds
     */
    public List<Integer> getUpsellIds() {
        return upsellIds;
    }

    /**
     *
     * @param upsellIds
     * The upsell_ids
     */
    public void setUpsellIds(List<Integer> upsellIds) {
        this.upsellIds = upsellIds;
    }

    /**
     *
     * @return
     * The crossSellIds
     */
    public List<Integer> getCrossSellIds() {
        return crossSellIds;
    }

    /**
     *
     * @param crossSellIds
     * The cross_sell_ids
     */
    public void setCrossSellIds(List<Integer> crossSellIds) {
        this.crossSellIds = crossSellIds;
    }

    /**
     *
     * @return
     * The parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     *
     * @param parentId
     * The parent_id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     *
     * @return
     * The purchaseNote
     */
    public String getPurchaseNote() {
        return purchaseNote;
    }

    /**
     *
     * @param purchaseNote
     * The purchase_note
     */
    public void setPurchaseNote(String purchaseNote) {
        this.purchaseNote = purchaseNote;
    }

    /**
     *
     * @return
     * The categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     * The categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     *
     * @return
     * The tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     * The tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     *
     * @return
     * The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The attributes
     */
    public List<Object> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     * The attributes
     */
    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return
     * The defaultAttributes
     */
    public List<Object> getDefaultAttributes() {
        return defaultAttributes;
    }

    /**
     *
     * @param defaultAttributes
     * The default_attributes
     */
    public void setDefaultAttributes(List<Object> defaultAttributes) {
        this.defaultAttributes = defaultAttributes;
    }

    /**
     *
     * @return
     * The variations
     */
    public List<Object> getVariations() {
        return variations;
    }

    /**
     *
     * @param variations
     * The variations
     */
    public void setVariations(List<Object> variations) {
        this.variations = variations;
    }

    /**
     *
     * @return
     * The groupedProducts
     */
    public List<Object> getGroupedProducts() {
        return groupedProducts;
    }

    /**
     *
     * @param groupedProducts
     * The grouped_products
     */
    public void setGroupedProducts(List<Object> groupedProducts) {
        this.groupedProducts = groupedProducts;
    }

    /**
     *
     * @return
     * The menuOrder
     */
    public Integer getMenuOrder() {
        return menuOrder;
    }

    /**
     *
     * @param menuOrder
     * The menu_order
     */
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Product(List<Image> images, String name, String price) {
        this.images = images;
        this.name = name;
        this.price = price;
    }
}