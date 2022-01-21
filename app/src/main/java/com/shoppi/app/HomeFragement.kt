package com.shoppi.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.shoppi.app.Module.GlideApp
import org.json.JSONObject

class HomeFragement: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
//        button.setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_product_detail)
//        }

        val assetLoader = AssetLoader()
        val homeData = assetLoader.getJsonString(requireContext(), "home.json")

        if(!homeData.isNullOrEmpty()){
            val jsonObject = JSONObject(homeData)
            val title = jsonObject.getJSONObject("title")
            val text = title.getString("text")
            val iconUrl = title.getString("icon_url")
            val titleValue = Title(text, iconUrl)

            val tvAppbarTitle = view.findViewById<TextView>(R.id.tv_appbar_title)
            tvAppbarTitle.text = titleValue.text

            val ivAppLogo = view.findViewById<ImageView>(R.id.iv_appbar_logo)
            GlideApp.with(view).load(titleValue.iconUrl).centerCrop().into(ivAppLogo)
        }
    }
}