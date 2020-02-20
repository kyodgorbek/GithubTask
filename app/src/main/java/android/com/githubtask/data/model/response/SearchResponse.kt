package android.com.githubtask.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int? = null,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    val items: List<ItemsItem>? = null
) : Serializable