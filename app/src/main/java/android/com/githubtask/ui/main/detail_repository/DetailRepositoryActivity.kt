package android.com.githubtask.ui.main.detail_repository

import android.com.githubtask.R
import android.com.githubtask.data.model.response.ItemsItem
import android.com.githubtask.databinding.ActivityDetailRepositoryBinding
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class DetailRepositoryActivity : AppCompatActivity() {

    private var viewBinding: ActivityDetailRepositoryBinding? = null

    companion object {

        private const val ITEMS_ITEM = "items_item"

        fun getStartIntent(context: Context, item: ItemsItem) =
            Intent(context, DetailRepositoryActivity::class.java).apply {
                putExtra(ITEMS_ITEM, item)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_repository)

        showDetailsRepository()
    }

    private fun showDetailsRepository() {
        (intent?.getSerializableExtra(ITEMS_ITEM) as? ItemsItem)?.let { item ->
            viewBinding?.let {
                it.item = item
            }
        }
    }
}
