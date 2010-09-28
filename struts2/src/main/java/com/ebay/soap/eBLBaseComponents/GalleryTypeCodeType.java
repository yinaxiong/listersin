
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GalleryTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GalleryTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Featured"/>
 *     &lt;enumeration value="Gallery"/>
 *     &lt;enumeration value="Plus"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GalleryTypeCodeType")
@XmlEnum
public enum GalleryTypeCodeType {


    /**
     * 
     * 						Listing image is not included in the search results
     * 						(in either Picture Gallery or List Views), and no other
     * 						image listing enhancements apply. Items that have pictures
     * 						but do not have image listing enhancements display a generic
     * 						photo image in search results.
     * 						<br><br>
     * 						Mainly useful for RelistItem use cases when you want to
     * 						remove an existing gallery setting. However, not all image
     * 						listing enhancements can be removed on revise or relist.
     * 						<br><br>
     * 						Note that specifying None is not necessarily the same as
     * 						omitting GalleryType from a request. For example, specifying
     * 						other item properties may cause the listing to be
     * 						included in the search results Gallery by default if
     * 						GalleryType is not specified.
     * 					
     * 
     */
    @XmlEnumValue("None")
    NONE("None"),

    /**
     * 
     * 						Highlights the listing at the top of the search results.
     * 						(No longer available for the US site; also, no longer available
     * 						on the US eBay Motors site, in the Parts and Accessories category.
     * 						In these cases, listings containing Featured First are listed,
     * 						but Featured First is dropped and a warning is returned.)
     * 						<br><br>
     * 						Additionally, the listing
     * 						gets the Gallery and Gallery Plus
     * 						functionality. Note that "Gallery" and "Plus" do not need to be
     * 						specified separately in a listing. Passing "Featured",
     * 						"Plus", and "Gallery" in the same request causes an error.
     * 						<br><br>
     * 						For the eBay Motors site (site ID 100), passing "Featured"
     * 						may put certain items on the eBay Motors home page.
     * 						<span class="tablenote"><b>Note:</b>
     * 						"Gallery Featured" was replaced by "Featured First" on several eBay sites,
     * 						including UK, IE, DE, AT, CH, and IT.
     * 						</span>
     * 						<br><br>
     * 					
     * 
     */
    @XmlEnumValue("Featured")
    FEATURED("Featured"),

    /**
     * 
     * 						Listing image is included in the search results (in
     * 						both Picture Gallery and List Views). Items that have
     * 						pictures but do not have the Gallery feature will have a
     * 						generic photo image shown in search results.
     * 						<br><br>
     * 						Some listing enhancements (for example, "Featured" and
     * 						"Plus") automatically include the Gallery functionality, so
     * 						"Gallery" does not need to be specified separately in the
     * 						listing. Passing "Featured", "Plus", and "Gallery" in the
     * 						same request will return an error.
     * 					
     * 
     */
    @XmlEnumValue("Gallery")
    GALLERY("Gallery"),

    /**
     * 
     * 						Listing image has Gallery Plus funtionality. That is, listing includes a Gallery Plus
     * 						icon that, when moused over or clicked, displays a large (400 px x 400 px) preview
     * 						image of the item. Clicking the image displays the View Item page for that listing.
     * 						<br><br>
     * 						Listing images that are originally smaller than 400 px x 400 px are centered in the
     * 						preview frame. Images that are originally larger than 400 px x 400 px are scaled down
     * 						to 400 px on their longest side (maintaining their original aspect ratio).
     * 						<br><br>
     * 						Note that you can use the Gallery Plus feature whether or not the image is hosted with
     * 						eBay Picture Services (EPS). One EPS image is included at no cost with the Gallery Plus
     * 						feature.
     * 						<br><br>
     * 						When "Plus" is included in an item listing, the listing also automatically gets the
     * 						Gallery functionality at no extra cost. "Gallery" does not need to be specified
     * 						separately in the listing. Passing "Featured", "Plus", and "Gallery" in the same
     * 						request will return an error.
     * 						<br><br>
     * 				    <span class="tablenote"><b>Note:</b>
     * 						The following sites offer free Gallery: US (site ID 0), the Parts & Accessories
     * 						Category on US Motors (site ID 100), CA (site ID 2), CAFR (site ID 210),
     * 						ES (site ID 186), FR (site ID 71), IT (site ID 101),and NL (site ID 146).
     * 						<br><br>
     * 						On these sites (starting on the same production date), whenever Plus is selected
     * 						in a request that specifies at least two eBay Picture Services (EPS) hosted
     * 						images (using ItemType.PictureDetailsType.PictureURL), the Gallery Plus feature
     * 						now automatically includes a Gallery Showcase of all the listing's
     * 						images at no extra cost. If Plus is selected and the request includes only one
     * 						EPS image or any self-hosted images, regular Gallery Plus applies.
     * 						<br><br>
     * 						The Gallery Showcase displays when mousing over or clicking on the listing's Gallery
     * 						Plus icon in the search results. The Showcase window displays a large
     * 						(400 px x 400 px) preview image which is usually the first specified
     * 						PictureURL, as well as up to four (64 px x 64 px) selectable
     * 						thumbnails for the remaining EPS images (the Showcase window contains
     * 						pagination controls for observing all EPS images in the request).
     * 						Clicking on the preview image displays the item's listing page.
     * 						<br><br>
     * 						When using RelistItem or ReviseItem (item has no bids and more than
     * 						12 hours before the listing's end), Plus can be unselected in
     * 						the request. However, the Plus fee will still apply if a previous
     * 						request selected Plus. There is at most one Plus fee per listing.
     * 						</span>
     * 						<br><br>
     * 						Not applicable to eBay Stores Inventory listings.
     * 					
     * 
     */
    @XmlEnumValue("Plus")
    PLUS("Plus"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    GalleryTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GalleryTypeCodeType fromValue(String v) {
        for (GalleryTypeCodeType c: GalleryTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
