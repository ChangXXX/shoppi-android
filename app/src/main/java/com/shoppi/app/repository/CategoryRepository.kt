package com.shoppi.app.repository

import com.shoppi.app.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val remoteDataSource: CategoryRemoteDataSource) {

    suspend fun getCategories(): List<Category> {
        //retrofit에서 아래 과정 대신 해줌
//        withContext(Dispatchers.IO){
//            remoteDataSource.getCategories()
//        }
        return remoteDataSource.getCategories()
    }
}