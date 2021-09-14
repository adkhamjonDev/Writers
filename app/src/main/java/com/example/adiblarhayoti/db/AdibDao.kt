package com.example.adiblarhayoti.db
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface AdibDao {
    @Insert
    fun addUser(adibUrl: AdibUrl)
    @Query("select * from adib")
    fun getUsers(): List<AdibUrl>
    @Delete
    fun deleteUser(adibUrl: AdibUrl)

}