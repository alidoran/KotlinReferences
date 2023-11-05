package com.example.commonlibrary.fake_endpoint

import com.example.commonlibrary.R


fun generateMessageList(size: Int): List<ListModel> =
    List(size) { i ->
        ListModel(
            "FirstLine $i",
            "SecondLine $i This is the Second long line to testing a compose list view that includes long data on each row"
        )
    }

fun generatePictureList(size: Int): List<PictureModel> =
    List(size) { i -> PictureModel(R.drawable.baseline_thumb_up_inverse_24dp, "Title $i") }

fun generateCheckList(size: Int): List<CheckListModel> =
    List(size) { i -> CheckListModel(i,"Title$i", i % 2 == 0) }

