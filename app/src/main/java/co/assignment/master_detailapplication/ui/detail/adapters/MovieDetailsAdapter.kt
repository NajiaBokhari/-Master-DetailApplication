package co.assignment.master_detailapplication.ui.detail.adapters

import androidx.databinding.ViewDataBinding
import co.assignment.master_detailapplication.R
import co.assignment.master_detailapplication.base.BaseBindingRecyclerAdapter
import co.assignment.master_detailapplication.databinding.ItemCastBinding
import co.assignment.master_detailapplication.ui.adapters.AdapterItemType

class MovieDetailsAdapter (
    private val list: MutableList<String>, val adapterItemType: AdapterItemType
) : BaseBindingRecyclerAdapter<String, MovieDetailsItemViewHolder>(
    list
) {

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.item_cast

    override fun onCreateViewHolder(binding: ViewDataBinding): MovieDetailsItemViewHolder {
        return MovieDetailsItemViewHolder(
            binding as ItemCastBinding
        )
    }

    override fun onBindViewHolder(holder: MovieDetailsItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position,adapterItemType)
    }

}
