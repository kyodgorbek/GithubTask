package android.com.githubtask.util

import android.com.githubtask.R
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:avatarByUrl")
fun CircleImageView.avatarByUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.ic_git)
        .into(this)
}