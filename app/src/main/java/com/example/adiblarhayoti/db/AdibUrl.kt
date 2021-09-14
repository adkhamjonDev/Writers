package com.example.adiblarhayoti.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "adib")
class AdibUrl:Serializable{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var fullName:String?=null
    var dateOfBirth:String?=null
    var category:String?=null
    var imgUrl:String?=null
    var description:String?=null
    var saved:Boolean?=false
    constructor(
        fullName: String?,
        dateOfBirth: String?,
        category:String?,
        imgUrl: String?,
        description: String?,
        saved: Boolean?
    ) {
        this.fullName = fullName
        this.dateOfBirth = dateOfBirth
        this.category=category
        this.imgUrl = imgUrl
        this.description = description
        this.saved = saved
    }
}