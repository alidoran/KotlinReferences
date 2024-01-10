package android.test.fragment_test


import android.os.Bundle
import android.test.databinding.ActivityFragmentBinding
import androidx.appcompat.app.AppCompatActivity

class TestFragmentActivity : AppCompatActivity() {

    private var fragment = TestFragment()
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
        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    private fun closeFragment() {
        supportFragmentManager.beginTransaction().remove(binding.fragmentContainer.getFragment())
            .commit()
        fragment = TestFragment()
    }
}