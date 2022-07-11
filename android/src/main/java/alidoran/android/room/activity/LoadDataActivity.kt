package alidoran.android.room.activity


import alidoran.android.databinding.ActivityLoadDataBinding
import alidoran.android.room.dao.AppDatabase.Companion.getDatabase
import alidoran.android.room.model.Contact
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import android.view.View

class LoadDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }


    private fun initEvent() {
        binding.txtName.onFocusChangeListener = OnFocusChangeListener { _: View?, _: Boolean ->
            val phoneNumber = binding.edtPhomeNumber.text.toString()
            val contactList: List<Contact>?  = getDatabase(this)
                .contactDao()
                .findByPhone(phoneNumber) as List<Contact>?
            if (contactList!!.isEmpty()) Toast.makeText(this, "Not found", Toast.LENGTH_SHORT)
                .show() else if (contactList.size > 1) Toast.makeText(
                this,
                "Duplicate phone",
                Toast.LENGTH_SHORT
            ).show() else {
                binding.txtName.text = contactList[0].firstName
                binding.txtLastName.text = contactList[0].lastName
            }
        }
    }
}