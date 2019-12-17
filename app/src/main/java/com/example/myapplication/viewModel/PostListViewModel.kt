package com.example.myapplication.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.database.PostApi
import com.example.myapplication.model.database.PostDao
import com.example.myapplication.R
import com.example.myapplication.model.Post
import com.example.myapplication.model.Posts
import com.example.myapplication.view.PostsAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao, val repository: PostRepository):
    BaseViewModel(){
    private final val PATH = "59ac28a9100000ce0bf9c236"
    @Inject
    lateinit var postApi: PostApi
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val postListAdapter: PostsAdapter =
        PostsAdapter()
    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { repository.getPost(2)}
            .concatMap {
                    dbPostList ->
                if(dbPostList.posts.isEmpty())
                    postApi.getPosts(PATH).concatMap {
                            apiPostList -> postDao.insertAll(*apiPostList.posts.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribeWith(object : DisposableObserver<Posts>(){

                override fun onNext(t: Posts) {
                    onRetrievePostListSuccess(t.posts)
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR", e.message!!)
                    onRetrievePostListError()
                }

            })
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE

    }

    private fun onRetrievePostListSuccess(postList:List<Post>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}