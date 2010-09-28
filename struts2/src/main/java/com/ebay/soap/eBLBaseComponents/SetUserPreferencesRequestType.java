
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Sets the authenticated user's preferences to those specified in the request.
 * 			
 * 
 * <p>Java class for SetUserPreferencesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetUserPreferencesRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="BidderNoticePreferences" type="{urn:ebay:apis:eBLBaseComponents}BidderNoticePreferencesType" minOccurs="0"/>
 *         &lt;element name="CombinedPaymentPreferences" type="{urn:ebay:apis:eBLBaseComponents}CombinedPaymentPreferencesType" minOccurs="0"/>
 *         &lt;element name="CrossPromotionPreferences" type="{urn:ebay:apis:eBLBaseComponents}CrossPromotionPreferencesType" minOccurs="0"/>
 *         &lt;element name="SellerPaymentPreferences" type="{urn:ebay:apis:eBLBaseComponents}SellerPaymentPreferencesType" minOccurs="0"/>
 *         &lt;element name="SellerFavoriteItemPreferences" type="{urn:ebay:apis:eBLBaseComponents}SellerFavoriteItemPreferencesType" minOccurs="0"/>
 *         &lt;element name="EndOfAuctionEmailPreferences" type="{urn:ebay:apis:eBLBaseComponents}EndOfAuctionEmailPreferencesType" minOccurs="0"/>
 *         &lt;element name="EmailShipmentTrackingNumberPreference" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UnpaidItemAssistancePreferences" type="{urn:ebay:apis:eBLBaseComponents}UnpaidItemAssistancePreferencesType" minOccurs="0"/>
 *         &lt;element name="PurchaseReminderEmailPreferences" type="{urn:ebay:apis:eBLBaseComponents}PurchaseReminderEmailPreferencesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetUserPreferencesRequestType", propOrder = {
    "bidderNoticePreferences",
    "combinedPaymentPreferences",
    "crossPromotionPreferences",
    "sellerPaymentPreferences",
    "sellerFavoriteItemPreferences",
    "endOfAuctionEmailPreferences",
    "emailShipmentTrackingNumberPreference",
    "unpaidItemAssistancePreferences",
    "purchaseReminderEmailPreferences"
})
public class SetUserPreferencesRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "BidderNoticePreferences")
    protected BidderNoticePreferencesType bidderNoticePreferences;
    @XmlElement(name = "CombinedPaymentPreferences")
    protected CombinedPaymentPreferencesType combinedPaymentPreferences;
    @XmlElement(name = "CrossPromotionPreferences")
    protected CrossPromotionPreferencesType crossPromotionPreferences;
    @XmlElement(name = "SellerPaymentPreferences")
    protected SellerPaymentPreferencesType sellerPaymentPreferences;
    @XmlElement(name = "SellerFavoriteItemPreferences")
    protected SellerFavoriteItemPreferencesType sellerFavoriteItemPreferences;
    @XmlElement(name = "EndOfAuctionEmailPreferences")
    protected EndOfAuctionEmailPreferencesType endOfAuctionEmailPreferences;
    @XmlElement(name = "EmailShipmentTrackingNumberPreference")
    protected Boolean emailShipmentTrackingNumberPreference;
    @XmlElement(name = "UnpaidItemAssistancePreferences")
    protected UnpaidItemAssistancePreferencesType unpaidItemAssistancePreferences;
    @XmlElement(name = "PurchaseReminderEmailPreferences")
    protected PurchaseReminderEmailPreferencesType purchaseReminderEmailPreferences;

    /**
     * Gets the value of the bidderNoticePreferences property.
     * 
     * @return
     *     possible object is
     *     {@link BidderNoticePreferencesType }
     *     
     */
    public BidderNoticePreferencesType getBidderNoticePreferences() {
        return bidderNoticePreferences;
    }

    /**
     * Sets the value of the bidderNoticePreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link BidderNoticePreferencesType }
     *     
     */
    public void setBidderNoticePreferences(BidderNoticePreferencesType value) {
        this.bidderNoticePreferences = value;
    }

    /**
     * Gets the value of the combinedPaymentPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link CombinedPaymentPreferencesType }
     *     
     */
    public CombinedPaymentPreferencesType getCombinedPaymentPreferences() {
        return combinedPaymentPreferences;
    }

    /**
     * Sets the value of the combinedPaymentPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link CombinedPaymentPreferencesType }
     *     
     */
    public void setCombinedPaymentPreferences(CombinedPaymentPreferencesType value) {
        this.combinedPaymentPreferences = value;
    }

    /**
     * Gets the value of the crossPromotionPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link CrossPromotionPreferencesType }
     *     
     */
    public CrossPromotionPreferencesType getCrossPromotionPreferences() {
        return crossPromotionPreferences;
    }

    /**
     * Sets the value of the crossPromotionPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrossPromotionPreferencesType }
     *     
     */
    public void setCrossPromotionPreferences(CrossPromotionPreferencesType value) {
        this.crossPromotionPreferences = value;
    }

    /**
     * Gets the value of the sellerPaymentPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link SellerPaymentPreferencesType }
     *     
     */
    public SellerPaymentPreferencesType getSellerPaymentPreferences() {
        return sellerPaymentPreferences;
    }

    /**
     * Sets the value of the sellerPaymentPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellerPaymentPreferencesType }
     *     
     */
    public void setSellerPaymentPreferences(SellerPaymentPreferencesType value) {
        this.sellerPaymentPreferences = value;
    }

    /**
     * Gets the value of the sellerFavoriteItemPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link SellerFavoriteItemPreferencesType }
     *     
     */
    public SellerFavoriteItemPreferencesType getSellerFavoriteItemPreferences() {
        return sellerFavoriteItemPreferences;
    }

    /**
     * Sets the value of the sellerFavoriteItemPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellerFavoriteItemPreferencesType }
     *     
     */
    public void setSellerFavoriteItemPreferences(SellerFavoriteItemPreferencesType value) {
        this.sellerFavoriteItemPreferences = value;
    }

    /**
     * Gets the value of the endOfAuctionEmailPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link EndOfAuctionEmailPreferencesType }
     *     
     */
    public EndOfAuctionEmailPreferencesType getEndOfAuctionEmailPreferences() {
        return endOfAuctionEmailPreferences;
    }

    /**
     * Sets the value of the endOfAuctionEmailPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndOfAuctionEmailPreferencesType }
     *     
     */
    public void setEndOfAuctionEmailPreferences(EndOfAuctionEmailPreferencesType value) {
        this.endOfAuctionEmailPreferences = value;
    }

    /**
     * Gets the value of the emailShipmentTrackingNumberPreference property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmailShipmentTrackingNumberPreference() {
        return emailShipmentTrackingNumberPreference;
    }

    /**
     * Sets the value of the emailShipmentTrackingNumberPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmailShipmentTrackingNumberPreference(Boolean value) {
        this.emailShipmentTrackingNumberPreference = value;
    }

    /**
     * Gets the value of the unpaidItemAssistancePreferences property.
     * 
     * @return
     *     possible object is
     *     {@link UnpaidItemAssistancePreferencesType }
     *     
     */
    public UnpaidItemAssistancePreferencesType getUnpaidItemAssistancePreferences() {
        return unpaidItemAssistancePreferences;
    }

    /**
     * Sets the value of the unpaidItemAssistancePreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnpaidItemAssistancePreferencesType }
     *     
     */
    public void setUnpaidItemAssistancePreferences(UnpaidItemAssistancePreferencesType value) {
        this.unpaidItemAssistancePreferences = value;
    }

    /**
     * Gets the value of the purchaseReminderEmailPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseReminderEmailPreferencesType }
     *     
     */
    public PurchaseReminderEmailPreferencesType getPurchaseReminderEmailPreferences() {
        return purchaseReminderEmailPreferences;
    }

    /**
     * Sets the value of the purchaseReminderEmailPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseReminderEmailPreferencesType }
     *     
     */
    public void setPurchaseReminderEmailPreferences(PurchaseReminderEmailPreferencesType value) {
        this.purchaseReminderEmailPreferences = value;
    }

}
