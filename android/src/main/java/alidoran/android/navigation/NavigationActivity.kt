package alidoran.android.navigation

import alidoran.android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
1- Resource manager -> navigation tab -> Add bottom + ->  create by lowercase name
2- Add -> create new destination -> uncheck factory methods -> finish
3- again number two for creating another Navigation Fragment
*- Right click and choose start(home) fragment
4- Connect fragment in navigation visual
5- In activity xml graph search "NavHostFragment"
and drag it into xml layout
6- Read code from NavFragment01
*- For each arrow we can choose animation by visual tool

* -> optional
 */


class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }
}