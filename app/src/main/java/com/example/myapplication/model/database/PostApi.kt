package com.example.myapplication.model.database

import com.example.myapplication.model.Posts
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("/v2/{id}")
    fun getPosts(@Path("id") id : String):Observable<Posts>
}