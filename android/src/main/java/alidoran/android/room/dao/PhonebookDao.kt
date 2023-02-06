package alidoran.android.room.dao

import alidoran.android.room.model.Phonebook
import androidx.room.Dao
import androidx.room.Insert
//todo doran
@Dao
interface PhonebookDao {
    @Insert
    fun addPhoneBook(vararg phonebook:Phonebook)
}