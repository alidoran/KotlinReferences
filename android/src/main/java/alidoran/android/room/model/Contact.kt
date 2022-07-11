package alidoran.android.room.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

// orm model don't have Model in last of class name
@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val uid: Int?,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String
)