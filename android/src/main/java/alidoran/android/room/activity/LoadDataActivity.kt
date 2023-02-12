package alidoran.android.room.activity


import alidoran.android.databinding.ActivityLoadDataBinding
import alidoran.android.room.dao.AppDatabase.Companion.getDatabase
import alidoran.android.room.dao.ContactDao
import alidoran.android.room.model.Contact
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoadDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadDataBinding
    lateinit var contactDao: ContactDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initial()
        initEvent()
    }

    private fun initial() {
        contactDao = getDatabase(applicationContext).contactDao()
    }

    private fun initEvent() {
        binding.txtName.onFocusChangeListener =
            OnFocusChangeListener { _, _ ->
                val phoneNumber = binding.edtPhomeNumber.text.toString()
                val contactList = contactDao.findByPhone(phoneNumber)
                contactList?.let {
                    textNameChange(it)
                }
            }
    }

    private fun textNameChange(contactList: List<Contact>) {
        if (contactList.isEmpty())
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
        else if (contactList.size > 1)
            Toast.makeText(this, "Duplicate phone", Toast.LENGTH_SHORT).show()
        else {
            binding.txtName.text = contactList[0].firstName
            binding.txtLastName.text = contactList[0].lastName
        }
    }
}