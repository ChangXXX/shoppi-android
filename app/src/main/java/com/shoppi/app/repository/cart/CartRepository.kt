package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem
import com.shoppi.app.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val localDataSource: CartItemLocalDataSource,
    private val ioDispatchr: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addCartItem(product: Product) {
        withContext(ioDispatchr) {
            val cartItem = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            localDataSource.addCartItem(cartItem)
        }
    }

    suspend fun getCartItems(): List<CartItem> {
        return withContext(ioDispatchr) { //viewmodel 에선 UI 스레드로  메소드를 호출할 것이고 이후 데이터를 받아 UI를 갱신하는 처리를 해서 데이터 요청하는 부분만 IO 스레드로 변경
            localDataSource.getCartItems()
        }
    }
}