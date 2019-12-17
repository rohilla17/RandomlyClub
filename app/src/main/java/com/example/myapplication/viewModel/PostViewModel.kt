package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Post
import java.text.SimpleDateFormat
import java.util.*

class PostViewModel: BaseViewModel() {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private val eventName = MutableLiveData<String>()
    private val eventDate = MutableLiveData<String>()
    private val views = MutableLiveData<String>()
    private val likes = MutableLiveData<String>()
    private val shares = MutableLiveData<String>()
    private val thumbnailImage = MutableLiveData<String>()


    fun bind(post: Post){
        eventDate.value = formatter.format(Date(post.event_date*1000))
        eventName.value = post.event_name
        views.value = post.views.toString()
        likes.value = post.likes.toString()
        shares.value = post.shares.toString()
        thumbnailImage.value = post.thumbnail_image
    }

    fun getEventName():MutableLiveData<String>{
        return eventName
    }

    fun getEventDate():MutableLiveData<String>{
        return eventDate
    }

    fun getLikes():MutableLiveData<String>{
        return likes
    }

    fun getViews():MutableLiveData<String>{
        return views
    }

    fun getThumbnailImage():MutableLiveData<String>{
        return thumbnailImage
    }

    fun getShares(): MutableLiveData<String>{
        return shares
    }

}