package android.test

//@ExperimentalCoroutinesApi
//inline fun <reified T : Fragment> launchFragmentInHiltContainer(
//    fragmentArgs: Bundle? = null,
//    themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
//    fragmentFactory: FragmentFactory? = null,
//    crossinline action: T.() -> Unit = {}
//) {
//    val mainActivityIntent = Intent.makeMainActivity(
//        ComponentName(
//            ApplicationProvider.getApplicationContext(),
//            HiltTestActivity::class.java
//        )
//    ).putExtra(FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY, themeResId)
//
//    ActivityScenario.launch<HiltTestActivity>(mainActivityIntent).onActivity { activity ->
//        fragmentFactory?.let {
//            activity.supportFragmentManager.fragmentFactory = it
//        }
//        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
//            Preconditions.checkNotNull(T::class.java.classLoader),
//            T::class.java.name
//        )
//        fragment.arguments = fragmentArgs
//
//        activity.supportFragmentManager.beginTransaction()
//            .add(android.R.id.content, fragment, "")
//            .commitNow()
//
//        (fragment as T).action()
//    }

//}