package alidoran.android.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phonebook")
data class Phonebook (
    @PrimaryKey(autoGenerate = true)
    val uid: Int?,
    val owner: Int,
    val contactIdList: List<Int>,
)