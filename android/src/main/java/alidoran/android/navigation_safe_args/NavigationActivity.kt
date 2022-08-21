package alidoran.android.navigation_safe_args

import alidoran.android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
dependencies
    implementation 'androidx.navigation:navigation-fragment-ktx:x.x.x'
    implementation 'androidx.navigation:navigation-ui-ktx:x.x.x'
    implementation "com.google.android.material:material:x.x"

1- Resource manager -> navigation tab -> Add bottom + ->  create by lowercase name
2- Add -> create new destination -> uncheck factory methods -> finish
3- again number two for creating another Navigation Fragment
*- Right click and choose start(home) fragment
4- Connect fragment in navigation visual
5- In activity xml (design mode) graph search "NavHostFragment"
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