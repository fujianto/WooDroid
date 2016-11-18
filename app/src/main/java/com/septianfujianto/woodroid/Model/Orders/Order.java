package com.septianfujianto.woodroid.Model.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Septian A. Fujianto on 11/14/2016.
 */

public class Order {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order_key")
    @Expose
    private String orderKey;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("prices_include_tax")
    @Expose
    private Boolean pricesIncludeTax;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("discount_total")
    @Expose
    private String discountTotal;
    @SerializedName("discount_tax")
    @Expose
    private String discountTax;
    @SerializedName("shipping_total")
    @Expose
    private String shippingTotal;
    @SerializedName("shipping_tax")
    @Expose
    private String shippingTax;
    @SerializedName("cart_tax")
    @Expose
    private String cartTax;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("billing")
    @Expose
    private Billing billing;
    @SerializedName("shipping")
    @Expose
    private Shipping shipping;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("payment_method_title")
    @Expose
    private String paymentMethodTitle;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("customer_ip_address")
    @Expose
    private String customerIpAddress;
    @SerializedName("customer_user_agent")
    @Expose
    private String customerUserAgent;
    @SerializedName("created_via")
    @Expose
    private String createdVia;
    @SerializedName("customer_note")
    @Expose
    private String customerNote;
    @SerializedName("date_completed")
    @Expose
    private String dateCompleted;
    @SerializedName("date_paid")
    @Expose
    private String datePaid;
    @SerializedName("cart_hash")
    @Expose
    private String cartHash;
    @SerializedName("line_items")
    @Expose
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    @SerializedName("tax_lines")
    @Expose
    private List<Object> taxLines = new ArrayList<Object>();
    @SerializedName("shipping_lines")
    @Expose
    private List<ShippingLine> shippingLines = new ArrayList<ShippingLine>();
    @SerializedName("fee_lines")
    @Expose
    private List<Object> feeLines = new ArrayList<Object>();
    @SerializedName("coupon_lines")
    @Expose
    private List<Object> couponLines = new ArrayList<Object>();
    @SerializedName("refunds")
    @Expose
    private List<Object> refunds = new ArrayList<Object>();
    @SerializedName("_links")
    @Expose
    private Links links;

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
     * The orderKey
     */
    public String getOrderKey() {
        return orderKey;
    }

    /**
     *
     * @param orderKey
     * The order_key
     */
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    /**
     *
     * @return
     * The number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * @param number
     * The number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     * The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     * The pricesIncludeTax
     */
    public Boolean getPricesIncludeTax() {
        return pricesIncludeTax;
    }

    /**
     *
     * @param pricesIncludeTax
     * The prices_include_tax
     */
    public void setPricesIncludeTax(Boolean pricesIncludeTax) {
        this.pricesIncludeTax = pricesIncludeTax;
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
     * The customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     * The customer_id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     * The discountTotal
     */
    public String getDiscountTotal() {
        return discountTotal;
    }

    /**
     *
     * @param discountTotal
     * The discount_total
     */
    public void setDiscountTotal(String discountTotal) {
        this.discountTotal = discountTotal;
    }

    /**
     *
     * @return
     * The discountTax
     */
    public String getDiscountTax() {
        return discountTax;
    }

    /**
     *
     * @param discountTax
     * The discount_tax
     */
    public void setDiscountTax(String discountTax) {
        this.discountTax = discountTax;
    }

    /**
     *
     * @return
     * The shippingTotal
     */
    public String getShippingTotal() {
        return shippingTotal;
    }

    /**
     *
     * @param shippingTotal
     * The shipping_total
     */
    public void setShippingTotal(String shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    /**
     *
     * @return
     * The shippingTax
     */
    public String getShippingTax() {
        return shippingTax;
    }

    /**
     *
     * @param shippingTax
     * The shipping_tax
     */
    public void setShippingTax(String shippingTax) {
        this.shippingTax = shippingTax;
    }

    /**
     *
     * @return
     * The cartTax
     */
    public String getCartTax() {
        return cartTax;
    }

    /**
     *
     * @param cartTax
     * The cart_tax
     */
    public void setCartTax(String cartTax) {
        this.cartTax = cartTax;
    }

    /**
     *
     * @return
     * The total
     */
    public String getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The totalTax
     */
    public String getTotalTax() {
        return totalTax;
    }

    /**
     *
     * @param totalTax
     * The total_tax
     */
    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    /**
     *
     * @return
     * The billing
     */
    public Billing getBilling() {
        return billing;
    }

    /**
     *
     * @param billing
     * The billing
     */
    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    /**
     *
     * @return
     * The shipping
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     *
     * @param shipping
     * The shipping
     */
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    /**
     *
     * @return
     * The paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     *
     * @param paymentMethod
     * The payment_method
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     *
     * @return
     * The paymentMethodTitle
     */
    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    /**
     *
     * @param paymentMethodTitle
     * The payment_method_title
     */
    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    /**
     *
     * @return
     * The transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     *
     * @param transactionId
     * The transaction_id
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     *
     * @return
     * The customerIpAddress
     */
    public String getCustomerIpAddress() {
        return customerIpAddress;
    }

    /**
     *
     * @param customerIpAddress
     * The customer_ip_address
     */
    public void setCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
    }

    /**
     *
     * @return
     * The customerUserAgent
     */
    public String getCustomerUserAgent() {
        return customerUserAgent;
    }

    /**
     *
     * @param customerUserAgent
     * The customer_user_agent
     */
    public void setCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
    }

    /**
     *
     * @return
     * The createdVia
     */
    public String getCreatedVia() {
        return createdVia;
    }

    /**
     *
     * @param createdVia
     * The created_via
     */
    public void setCreatedVia(String createdVia) {
        this.createdVia = createdVia;
    }

    /**
     *
     * @return
     * The customerNote
     */
    public String getCustomerNote() {
        return customerNote;
    }

    /**
     *
     * @param customerNote
     * The customer_note
     */
    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    /**
     *
     * @return
     * The dateCompleted
     */
    public String getDateCompleted() {
        return dateCompleted;
    }

    /**
     *
     * @param dateCompleted
     * The date_completed
     */
    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     *
     * @return
     * The datePaid
     */
    public String getDatePaid() {
        return datePaid;
    }

    /**
     *
     * @param datePaid
     * The date_paid
     */
    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    /**
     *
     * @return
     * The cartHash
     */
    public String getCartHash() {
        return cartHash;
    }

    /**
     *
     * @param cartHash
     * The cart_hash
     */
    public void setCartHash(String cartHash) {
        this.cartHash = cartHash;
    }

    /**
     *
     * @return
     * The lineItems
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     *
     * @param lineItems
     * The line_items
     */
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    /**
     *
     * @return
     * The taxLines
     */
    public List<Object> getTaxLines() {
        return taxLines;
    }

    /**
     *
     * @param taxLines
     * The tax_lines
     */
    public void setTaxLines(List<Object> taxLines) {
        this.taxLines = taxLines;
    }

    /**
     *
     * @return
     * The shippingLines
     */
    public List<ShippingLine> getShippingLines() {
        return shippingLines;
    }

    /**
     *
     * @param shippingLines
     * The shipping_lines
     */
    public void setShippingLines(List<ShippingLine> shippingLines) {
        this.shippingLines = shippingLines;
    }

    /**
     *
     * @return
     * The feeLines
     */
    public List<Object> getFeeLines() {
        return feeLines;
    }

    /**
     *
     * @param feeLines
     * The fee_lines
     */
    public void setFeeLines(List<Object> feeLines) {
        this.feeLines = feeLines;
    }

    /**
     *
     * @return
     * The couponLines
     */
    public List<Object> getCouponLines() {
        return couponLines;
    }

    /**
     *
     * @param couponLines
     * The coupon_lines
     */
    public void setCouponLines(List<Object> couponLines) {
        this.couponLines = couponLines;
    }

    /**
     *
     * @return
     * The refunds
     */
    public List<Object> getRefunds() {
        return refunds;
    }

    /**
     *
     * @param refunds
     * The refunds
     */
    public void setRefunds(List<Object> refunds) {
        this.refunds = refunds;
    }

    /**
     *
     * @return
     * The links
     */
    public Links getLinks() {
        return links;
    }

    /**
     *
     * @param links
     * The _links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    public Order(String payment_method, String payment_method_title) {
        this.paymentMethod = payment_method;
        this.paymentMethodTitle = payment_method_title;
    }
}
