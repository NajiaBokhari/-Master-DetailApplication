package co.assignment.master_detailapplication.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import co.assignment.master_detailapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["recycleViewAdapter", "grids", "checkItemSpan"], requireAll = false)

    fun setRecycleViewAdapter(
            recyclerView: RecyclerView,
            adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?,
            grids: Int = 1,
            checkItemSpan: Boolean = false
    ) {

        if (null == adapter)
            return
        recyclerView.adapter = adapter
        val gridLayoutManager = GridLayoutManager(recyclerView.context, grids)
        recyclerView.layoutManager = gridLayoutManager

        if (checkItemSpan) {
            gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if ((recyclerView.adapter?.itemCount)?.rem(2) != 0 && position == (recyclerView.adapter?.itemCount)?.minus(1)) grids else 1
                }
            }
        }
    }

    @BindingAdapter("src", "square")
    @JvmStatic
    fun setImageResId(view: AppCompatImageView, imageSrc: String, square: Boolean) {
        if (!square) {
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