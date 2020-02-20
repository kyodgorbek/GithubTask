package android.com.githubtask.data.model.response

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?
)