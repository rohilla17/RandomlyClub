package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post (var id : String, var thumbnail_image : String, @field:PrimaryKey var event_name : String, var event_date : Long,
                 var views : Int, var shares : Int, var likes : Int)