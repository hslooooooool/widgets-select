package vip.qsos.select

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_time_picker.view.*

/**
 * @author : 华清松
 *
 * 选择器案例列表项
 */
class SelectHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(data: String) {
        itemView.item_select_name.text = data
    }
}