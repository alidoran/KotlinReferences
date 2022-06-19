package android.room.dao

import androidx.room.*
import android.room.model.Contact

@Dao
interface ContactDao {
    @get:Query("SELECT * FROM contact")
    val all: List<Contact?>?
    @Query("SELECT * FROM contact WHERE uid IN (:contactIds)")

    fun loadAllByIds(contactIds: IntArray?): List<Contact?>?
    @Query(
        "SELECT * FROM contact WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )

    fun findByName(first: String?, last: String?): Contact?

    @Query("SELECT * FROM contact WHERE phone_number LIKE :phoneNumber LIMIT 1")
    fun findByPhone(phoneNumber: String?): Contact?

    @Insert
    fun insertAll(vararg contacts: Contact?): List<Long?>?

    @Update
    fun update(vararg contacts: Contact?)

    @Delete
    fun delete(contact: Contact?)
}