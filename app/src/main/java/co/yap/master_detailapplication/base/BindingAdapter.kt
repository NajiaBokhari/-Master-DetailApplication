package co.yap.master_detailapplication.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.yap.master_detailapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("recycleViewAdapter","grids")
    fun setRecycleViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?,
        grids:Int = 1
    ) {

        if (null == adapter)
            return
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, grids)
    }

    @BindingAdapter("src", "square")
    @JvmStatic
    fun setImageResId(view: AppCompatImageView, imageSrc: String, square: Boolean) {
        if (square) {
            Glide.with(view.context)
                .load(imageSrc).transforms(CenterCrop(), RoundedCorners(115))
                .placeholder(R.drawable.place_holder).transforms(CenterCrop(), RoundedCorners(115))
                .into(view)
        } else {

            Glide.with(view.context)
                .load(imageSrc).transforms(CenterCrop(), RoundedCorners(25))
                .placeholder(R.drawable.place_holder).transforms(CenterCrop(), RoundedCorners(25))
                .into(view)
        }
    }
}