package android.com.githubtask.ui.main.adapter

import android.com.githubtask.R
import android.com.githubtask.data.model.response.ItemsItem
import android.com.githubtask.databinding.ItemRepositoriesBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_repositories.view.*

class RepositoriesAdapter(
    private val onClick: (ItemsItem) -> Unit?
) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesHolder>() {

    var repositories = listOf<ItemsItem>()

    fun setData(list: List<ItemsItem>) {
        repositories = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesHolder {
        val binding: ItemRepositoriesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_repositories,
            parent, false
        )

        return RepositoriesHolder(binding)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoriesHolder, position: Int) {
        holder.bind(repositories[position])
    }

    inner class RepositoriesHolder(private val binding: ItemRepositoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemsItem) {
            binding.item = item
            binding.root.clRepository.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}