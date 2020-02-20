package android.com.githubtask.ui.main.fragment

import android.com.githubtask.R
import android.com.githubtask.data.model.response.ItemsItem
import android.com.githubtask.data.model.response.NetworkResponse
import android.com.githubtask.data.model.response.SearchResponse
import android.com.githubtask.ui.main.MainActivity
import android.com.githubtask.ui.main.MainActivityViewModel
import android.com.githubtask.ui.main.adapter.RepositoriesAdapter
import android.com.githubtask.ui.main.adapter.RepositoryDiffUtils
import android.com.githubtask.ui.main.detail_repository.DetailRepositoryActivity
import android.com.githubtask.util.gone
import android.com.githubtask.util.visible
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_repositories.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@Suppress("PLUGIN_WARNING")
class RepositoriesFragment : Fragment() {

    private val POSITION = "position"

    private val repositoryVM: MainActivityViewModel by sharedViewModel()
    private val adapter: RepositoriesAdapter by lazy { RepositoriesAdapter(this::onClickRepository) }

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        observeData()
        setListener()
    }

    private fun initRecycler() {
        activity?.let {
            rvRepositories.layoutManager = LinearLayoutManager(it)
            rvRepositories.adapter = adapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(POSITION, rvRepositories.layoutManager?.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            rvRepositories.post {
                rvRepositories.layoutManager?.onRestoreInstanceState(it.getParcelable(POSITION))
            }
        }
    }

    private fun setListener() {
        etMainSearch.setAfterTextWatcher {
            repositoryVM.searchRepository(it)
        }
    }

    private fun observeData() {
        activity?.let { context ->
            repositoryVM.getRepoData()
                .observe(context, Observer<NetworkResponse<SearchResponse>> { data ->
                    when (data) {
                        is NetworkResponse.Success -> {
                            data.data.items?.let { setAdapterDate(it) }
                        }
                        is NetworkResponse.Error -> {
                            setAdapterDate(listOf())
                            activity?.let {
                                Toast.makeText(it, data.error.message, Toast.LENGTH_LONG).show()
                            }
                        }
                        is NetworkResponse.Failure -> {
                            setAdapterDate(listOf())
                            activity?.let {
                                Toast.makeText(it, data.throwable.message, Toast.LENGTH_LONG).show()
                            }
                        }
                        is NetworkResponse.Loading -> {
                            if (data.state) {
                                pbDownload.visible()
                            } else {
                                pbDownload.gone()
                            }
                        }
                    }
                })
        }
    }

    private fun setAdapterDate(list: List<ItemsItem>) {
        scope.launch {
            val diffCallback = RepositoryDiffUtils(
                adapter.repositories,
                list
            )
            var diffResult: DiffUtil.DiffResult? = null
            withContext(Dispatchers.Default) {
                diffResult = DiffUtil.calculateDiff(diffCallback)
            }
            adapter.setData(list)
            diffResult?.dispatchUpdatesTo(adapter)
        }
    }


    private fun onClickRepository(items: ItemsItem) {
        activity?.let {
            (it as? MainActivity)?.startActivity(DetailRepositoryActivity.getStartIntent(it, items))
        }
    }
}