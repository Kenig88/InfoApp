package com.kenig.infoapp.db

import androidx.room.*
import androidx.room.Dao
import com.kenig.infoapp.utils.ListItem

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)
    @Delete
    suspend fun deleteItem(item: ListItem)
    @Query("SELECT * FROM main WHERE category LIKE :cat")
    suspend fun getAllItemsByCategory(cat: String): List<ListItem>
}