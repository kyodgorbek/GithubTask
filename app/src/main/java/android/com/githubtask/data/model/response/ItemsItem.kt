package android.com.githubtask.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ItemsItem(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("forks_count")
    val forksCount: Int? = null,
    @SerializedName("watchers_count")
    val watchersCount: Int? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (other is ItemsItem)
            return false

        val newItemsItem = other as ItemsItem

        return name == newItemsItem.name
                && description == newItemsItem.description
                && owner == newItemsItem.owner
                && forksCount == newItemsItem.forksCount
                && watchersCount == newItemsItem.watchersCount
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + description.hashCode()
        result = 31 * result + (owner.hashCode())
        result = 31 * result + (forksCount ?: 0)
        result = 31 * result + (watchersCount ?: 0)
        return result
    }

}