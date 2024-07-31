package com.mynewsapp.api

import android.util.Log
import com.google.gson.Gson
import com.mynewsapp.data.newsresponse.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")

    fun getAllNews(
        @Query("apiKey") apiKey: String, @Query("sortBy") sortBy: String, @Query("q") query: String, @Query("pageSize") pageSize: Int
    ): Call<NewsResponse>
}

class NewsApi() {
    val API_KEY = "a437ca886dc64e62a5f923fdc4c96ac4"
    fun getAllNews(
        callback: (NewsResponse) -> Unit,
        errorCallback: (message: String?) -> Unit = {}
    ) {
        val call: Call<NewsResponse> = RetrofitClient.newsApiService.getAllNews(apiKey = API_KEY, sortBy = "popularity", query = "technology", pageSize = 100)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("TAG", "======= The response from api ============")
                Log.d("TAG", response.toString())

                Log.d("TAG Response Message ", response.body().toString())
                Log.d("TAG successful ", response.isSuccessful.toString())

                if (response.isSuccessful) {
                    // If the response is successful, parse the
                    // response body to a DataModel object.
                    val newsResponse = response.body() as NewsResponse

                    // Call the callback function with the DataModel
                    // object as a parameter.
                    callback(newsResponse)
                } else {
                    val errorBody = response.errorBody()?.string()
                    val message = response.message()
                    val errorResponse: ErrorResponse? = try {
                        Gson().fromJson(errorBody, ErrorResponse::class.java)
                    } catch (e: Exception) {
                        null
                    }

                    if (errorResponse != null) {
                        val error = Throwable("Error: ${errorResponse.message} (Code: ${errorResponse.code})")
                        onFailure(call, error)
                    } else {
                        val error = Throwable("Error: $message, Details: $errorBody")
                        onFailure(call, error)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("ApiCall_new", "-----> ${t.message}")
                errorCallback(t.message)
            }
        })
    }
}

data class ErrorResponse(
    val status: String,
    val code: String,
    val message: String
)