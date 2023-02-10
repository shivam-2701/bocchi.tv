package com.example.bocchitv.MainPage

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.bocchitv.Models.Main.Result
import com.example.bocchitv.Models.Main.RowListItem


class RowListFragment : RowsSupportFragment() {

    private var rootAdapter : ArrayObjectAdapter= ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))
    private var itemSelectedListener: ((Result)->Unit)?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting up adapter from within the class
        adapter = rootAdapter

        //Setting up listener
        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    fun bindData(item: RowListItem){

        val arrayObjectAdapter= ArrayObjectAdapter(ItemPresenter())

        item.rowAnimeList.results.forEach{ it->
            arrayObjectAdapter.add(it)
        }
        val headerItem = HeaderItem(item.rowItemName)
        val listRow = ListRow(headerItem,arrayObjectAdapter)
        rootAdapter.add(listRow)

    }

    fun setOnContentSelectedListener(listener: (Result)->Unit){
        this.itemSelectedListener=listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener{

        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
//            TODO("Not yet implemented")
            if(item is Result){
                itemSelectedListener?.invoke(item)
            }
        }
    }

}