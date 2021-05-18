package ru.android.rickandmortymvvm.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import ru.android.rickandmortymvvm.R


fun ImageView.setImageFitPlaceholderWithGlide(
    imageUrl: String?,
    isRounded: Boolean
) {
    scaleType = ImageView.ScaleType.MATRIX
    val placeholder = ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)
    Glide
        .with(this)
        .load(imageUrl)
        .apply(getDefaultRequestOptions(context, isRounded, placeholder, placeholder))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                scaleType = ImageView.ScaleType.MATRIX
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                scaleType = ImageView.ScaleType.CENTER_CROP
                return false
            }
        })
        .into(this)
}

private fun getDefaultRequestOptions(
    context: Context,
    isRounded: Boolean,
    imagePlaceholder: Drawable?,
    imageError: Drawable?
) =
    RequestOptions().apply {
        placeholder(imagePlaceholder)
        error(imageError ?: context.getDrawable(R.color.white))
        if (isRounded) optionalCircleCrop()
    }