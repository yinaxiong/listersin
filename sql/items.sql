DROP TABLE IF EXISTS items;

CREATE TABLE `items` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `accountid` int(11) default NULL,
  `created` datetime default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    
  `CategoryMappingAllowed` tinyint(1) default NULL,
  `Country` char(2) default NULL,
  `Currency` char(3) default NULL,
  `Description` text,
  `DispatchTimeMax` int(11) default NULL,
  `ItemID` bigint(20) default NULL,
  `ListingDetails_EndTime` datetime default NULL,
  `ListingDetails_StartTime` datetime default NULL,
  `ListingDetails_ViewItemURL` varchar(500) default NULL,
  `ListingDuration` varchar(8) default NULL,
  `ListingType` varchar(20) default NULL,
  `Location` varchar(45) default NULL,
  `PaymentMethods` text,
  `PayPalEmailAddress` varchar(255) default NULL,
  `PictureDetails_GalleryType` varchar(8) default NULL,
  `PictureDetails_GalleryURL` varchar(255) default NULL,
  `PictureDetails_PictureURL` text,
  `PrimaryCategory_CategoryID` bigint(20) default NULL,
  `PrimaryCategory_CategoryName` varchar(200) default NULL,
  `Quantity` int(11) default NULL,
  `ReservePrice` double default NULL,
  `ReservePrice@currencyID` char(3) default NULL,
  `ReturnPolicy_Description` text,
  `ReturnPolicy_RefundOption` varchar(30) default NULL,
  `ReturnPolicy_ReturnsAcceptedOption` varchar(20) default NULL,
  `ReturnPolicy_ReturnsWithinOption` varchar(7) default NULL,
  `ReturnPolicy_ShippingCostPaidByOption` varchar(6) default NULL,
  `SellingStatus_CurrentPrice` double default NULL,
  `SellingStatus_CurrentPrice@currencyID` char(3) default NULL,
  `SellingStatus_QuantitySold` int(11) default NULL,
  `ShippingDetails_ShippingServiceOptions_ShippingServiceCost` double default NULL,
  `ShippingDetails_ShippingServiceOptions_ShippingServicePriority` int(11) default NULL,
  `ShippingDetails_ShippingServiceOptions_ShippingService` varchar(100) default NULL,
  `ShippingDetails_ShippingType` varchar(50) default NULL,
  `Site` varchar(20) default NULL,
  `StartPrice` double default NULL,
  `StartPrice@currencyID` char(3) default NULL,
  `SubTitle` varchar(55) default NULL,
  `Title` varchar(55) default NULL,

  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

desc items;
