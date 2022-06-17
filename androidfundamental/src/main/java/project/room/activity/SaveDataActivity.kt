package project.room.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android_fundamental.databinding.ActivitySaveDataBinding
import android.widget.Toast
import project.room.dao.AppDatabase.Companion.getDatabase
import project.room.model.Contact

class SaveDataActivity : AppCompatActivity() {
    lateinit var binding: ActivitySaveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() {

        binding.btnSaveData.setOnClickListener {
            var uid = -1
            val inputContact = createModel()

            val contactList = getDatabase(this)
                .contactDao()
                .all

            for (contact in contactList!!) {
                if (inputContact.firstName == contact!!.firstName
                    && inputContact.lastName == contact.lastName
                    || inputContact.phoneNumber == contact.phoneNumber
                ) {
                    uid = contact.uid!!
                    break
                }
            }
            if (uid == -1)
                getDatabase(this).contactDao().insertAll(inputContact)
            else
                getDatabase(this).contactDao().update(inputContact)

            Toast.makeText(this, contactList[0]!!.firstName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createModel(): Contact {
        val name = binding.edtName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val phoneNumber = binding.edtPhone.text.toString()
        return Contact(null, name, lastName, phoneNumber)
    }
}