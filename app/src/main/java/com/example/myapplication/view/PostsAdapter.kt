package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.viewModel.PostViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.PostItemBinding
import com.example.myapplication.model.Post
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader

public class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>(){
    private lateinit var postList:List<Post>

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(postList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: PostItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.post_item, parent, false)
        return PostViewHolder(binding)
    }

    fun updatePostList(postList:List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(item: Post) {
            viewModel.bind(item)
            binding.viewModel = viewModel
            setImage(binding.thumbnailImage, item.thumbnail_image)
        }

        fun setImage(imageView: ImageView, imageUrl: String) {
            var imageLoader = ImageLoader.getInstance()
            var imageOptions = DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true).build()
            imageLoader.displayImage(imageUrl, imageView, imageOptions)
        }
    }
}
