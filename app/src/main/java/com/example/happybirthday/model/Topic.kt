package com.example.happybirthday.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicRes: Int,
    val number: Int,
    @DrawableRes val drawableRes: Int
)
