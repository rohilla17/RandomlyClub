package com.example.myapplication.viewModel

import com.example.myapplication.model.database.PostDao
import com.example.myapplication.model.Posts

class PostRepository(val postDao: PostDao){

    fun getPost(page : Int) : Posts {
        var post = Posts(postDao.all, page)
        return post
    }
}