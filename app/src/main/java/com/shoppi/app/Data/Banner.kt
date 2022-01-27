package com.shoppi.app.Data

data class Banner(
    val backgroundImageUrl: String,
    val badge: BannerBadge,
    val label: String,
    val productDetail: ProductDetail,
)

data class BannerBadge(
    val label: String,
    val background_color: String
)

data class ProductDetail(
    val brandName: String,
    val label: String,
    val discountRate: Int,
    val price: Int,
    val thumbnailImageUrl: String,
    val productId: String
)