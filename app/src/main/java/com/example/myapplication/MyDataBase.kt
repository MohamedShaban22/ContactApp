package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Dao.ContactDao
import com.example.myapplication.model.ContactModel

@Database(entities = [ContactModel::class], version = 1, exportSchema = true)
abstract class MyDataBase:RoomDatabase() {
    abstract fun ContactDao():ContactDao

    companion object{
        private var instance:MyDataBase?=null
        fun getInstance(context: Context):MyDataBase{
            if(instance==null){
                instance= Room.databaseBuilder(context.applicationContext, MyDataBase::class.java, "tasksDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }    }