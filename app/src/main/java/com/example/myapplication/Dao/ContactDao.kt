package com.example.myapplication.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.model.ContactModel

@Dao
interface ContactDao {
    @Insert
    fun insertContact(contactModel:ContactModel)
    @Delete
    fun deleteContact(contactModel:ContactModel)
    @Update
    fun upDateContact(contactModel:ContactModel)
    @Query("SELECT * FROM ContactModel")
    fun getAllContacts():MutableList<ContactModel>

}