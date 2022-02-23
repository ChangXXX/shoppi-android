package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.GlideApp
import com.shoppi.app.R
import com.shoppi.app.common.KEY_PRDOUCT_ID
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.ui.common.EventObserver
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragement : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setToolbar()
        setTopBanners()
        setNavigation()
    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner, { title ->
            binding.title = title
        })
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner, { banners ->
                    submitList(banners)
                })
            }

            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset // 오른쪽 이동
            }
            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }

    private fun setNavigation() {
        viewModel.openProductDetailEvent.observe(viewLifecycleOwner, EventObserver { productId ->
            findNavController().navigate(
                R.id.action_home_to_product_detail, bundleOf(
                    KEY_PRDOUCT_ID to productId
                )
            )
        })
    }
}