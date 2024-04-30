package com.ruvik.find_interesting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruvik.find_interesting.api.RetrofitClient
import com.ruvik.find_interesting.data.Post
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindInterestingViewModel : ViewModel() {
    enum class Status private constructor(val code: Int) {
        BAD_RESPONSE(-1),
        FAILURE_RESPONSE(-2),READY(0),
        IN_PROGRESS(1),
        SUCCESS_RESPONSE(2),
        EMPTY_RESPONSE(3),
    }

    private val mPostsLoaded = MutableLiveData<Status>(Status.READY)
    val postLoaded: LiveData<Status> = mPostsLoaded

    private val mApiService = RetrofitClient.apiService

    private val mPosts = mutableListOf<Post>()

    fun find(text: String) {
        mPosts.clear()
        mPostsLoaded.postValue(Status.IN_PROGRESS)
        val call = mApiService.search(text)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val htmlResponse = response.body()
                    val document = htmlResponse?.let { it1 -> Jsoup.parse(it1) }

                    val posts = document?.select(".iom-post")
                    if (posts != null && posts.size > 0) {
                        for (post in posts) {
                            val date = document.selectFirst(".iom-post__info-item")?.text()
                                ?.substringAfterLast(" ")
                            val title = post.selectFirst(".iom-post__title")?.text()
                            val description = post.selectFirst(".iom-post__description")?.text()
                            val link = post.selectFirst(".iom-post__link")?.attr("href")
                            mPosts.add(
                                Post(
                                    title = title,
                                    description = description,
                                    link = link,
                                    date = date
                                )
                            )
                        }
                        mPostsLoaded.postValue(Status.SUCCESS_RESPONSE) // Нашлись совпадения
                        return
                    }
                    mPostsLoaded.postValue(Status.EMPTY_RESPONSE) // Нет совпадений
                } else {
                    mPostsLoaded.postValue(Status.BAD_RESPONSE) // Сервер не доступен
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                mPostsLoaded.postValue(Status.FAILURE_RESPONSE) // Ошибка сети
            }
        })
    }

    fun getData(): List<Post> = mPosts
}