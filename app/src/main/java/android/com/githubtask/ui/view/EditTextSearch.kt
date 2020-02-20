package android.com.githubtask.ui.view

import android.annotation.SuppressLint
import android.com.githubtask.R
import android.com.githubtask.util.RxSearch
import android.content.Context

import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.edit_text_search.view.*
import java.util.concurrent.TimeUnit

class EditTextSearch(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var afterTextWatcher: ((String) -> Unit)? = null

    init {
        initAttribute()
    }

    private fun getLayout() = R.layout.edit_text_search

    private fun initAttribute() {
        View.inflate(context, getLayout(), this)
        initTextWatcher()
        setListener()
    }

    fun setAfterTextWatcher(callback: (String) -> Unit) {
        afterTextWatcher = callback
    }

    private fun setListener() {
        btnSearch.setOnClickListener {
            if (etSearch.text.isNotEmpty()) etSearch.setText("")
        }
    }

    @SuppressLint("CheckResult")
    private fun initTextWatcher() {
        RxSearch(etSearch)
            .subscribeOn(Schedulers.io())
            .map {
                btnSearch.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        if (it.isEmpty()) R.drawable.ic_search else R.drawable.ic_close
                    )
                )
                it
            }
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it?.let { search ->
                    afterTextWatcher?.invoke(search.toString())
                }
            }
    }
}