package com.example.happybirthday

import android.app.Application
import com.example.happybirthday.marsphoto.data.AppContainer
import com.example.happybirthday.marsphoto.data.DefaultAppContainer

class MyApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}