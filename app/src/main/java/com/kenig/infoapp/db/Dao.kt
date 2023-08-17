package com.kenig.infoapp.db

import androidx.room.*
import androidx.room.Dao
import com.kenig.infoapp.utils.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)
    @Delete
    suspend fun deleteItem(item: ListItem)
    @Query("SELECT * FROM main WHERE category LIKE :cat")
    fun getAllItemsByCategory(cat: String): Flow<List<ListItem>>
    @Query("SELECT * FROM main WHERE isFav = 1")
    fun getFavorites(): Flow<List<ListItem>>
}