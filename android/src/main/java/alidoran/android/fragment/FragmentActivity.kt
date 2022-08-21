package alidoran.android.fragment

import alidoran.android.databinding.ActivityFragmentBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FragmentActivity : AppCompatActivity() {

    private var fragment = MyFragment()
    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment()
    }

    fun loadFragment() {
        if (fragment.isResumed)
            closeFragment()

        val bundle = Bundle()
        bundle.putBoolean("Pass data", true)
        bundle.putString("pass string", "My String")
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(binding.fragmentNavigationContainer.id, fragment)
            .commit()
    }

    private fun closeFragment() {
        supportFragmentManager.beginTransaction().remove(binding.fragmentNavigationContainer.getFragment())
            .commit()
        fragment = MyFragment()
    }
}