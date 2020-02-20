package android.com.githubtask.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Int
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (other is Owner)
            return false

        val newOwner = other as Owner

        return id == newOwner.id && avatarUrl == newOwner.avatarUrl
    }

    override fun hashCode(): Int {
        return avatarUrl.hashCode()
    }
}