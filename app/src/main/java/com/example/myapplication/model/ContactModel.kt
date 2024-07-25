package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name:String?=null,
    var phone:String?=null,
    var description:String?=null
)
