package com.example.adiblarhayoti.models

import java.io.Serializable

class WritersClass:Serializable {
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

    constructor()

}