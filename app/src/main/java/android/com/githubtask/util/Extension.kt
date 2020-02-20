package android.com.githubtask.util

import android.com.githubtask.data.model.response.ErrorResponse
import android.view.View
import com.google.gson.Gson
import retrofit2.Response

fun <T> Response<T>.parseErrorBody(): ErrorResponse =
    Gson().fromJson(errorBody()?.charStream(), ErrorResponse::class.java)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}