package com.example.commonlibrary.fake_endpoint

import androidx.annotation.DrawableRes

data class NameModel(val name: String)
data class PictureModel(@DrawableRes val image: Int, val title: String)
data class ListModel(val title: String, val body: String)
data class CheckListModel(val id: Int, val title: String, val isChecked: Boolean)
