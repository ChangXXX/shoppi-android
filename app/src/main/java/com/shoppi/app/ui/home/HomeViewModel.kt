package com.shoppi.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppi.app.model.Banner
import com.shoppi.app.model.Product
import com.shoppi.app.model.Promotion
import com.shoppi.app.model.Title
import com.shoppi.app.repository.home.HomeRepository
import com.shoppi.app.ui.common.Event

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _title = MutableLiveData<Title>()
    val title: LiveData<Title> = _title

    private val _topBanners = MutableLiveData<List<Banner>>()
    val topBanners: LiveData<List<Banner>> = _topBanners

    private val _promotions = MutableLiveData<Promotion>()
    val promotion: LiveData<Promotion> = _promotions

    private val _openProductDetailEvent = MutableLiveData<Event<String>>()
    val openProductDetailEvent: LiveData<Event<String>> = _openProductDetailEvent

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        val homeData = homeRepository.getHomeData()
        homeData?.let { homeData ->
            _title.value = homeData.title
            _topBanners.value = homeData.topBanners
            _promotions.value = homeData.promotions
        }
    }

    fun openProductDetail(productId: String) {
        _openProductDetailEvent.value = Event(productId)
    }
}