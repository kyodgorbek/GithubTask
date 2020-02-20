package android.com.githubtask.ui.main.adapter

import android.com.githubtask.data.model.response.ItemsItem
import androidx.recyclerview.widget.DiffUtil

class RepositoryDiffUtils(
    private val oldList: List<ItemsItem>,
    private val newList: List<ItemsItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.owner.id == newItem.owner.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}