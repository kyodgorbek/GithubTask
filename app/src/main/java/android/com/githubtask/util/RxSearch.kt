package android.com.githubtask.util

import android.text.Editable
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.Observer

class RxSearch(private val view: TextView) : Observable<CharSequence>() {

    override fun subscribeActual(observer: Observer<in CharSequence>) {
        view.addTextChangedListener(Listener(observer))
    }

    inner class Listener(private val observable: Observer<in CharSequence>) : DefaultTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            s?.let { observable.onNext(it) }
        }
    }
}