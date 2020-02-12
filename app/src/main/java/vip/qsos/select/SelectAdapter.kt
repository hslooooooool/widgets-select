package vip.qsos.select

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author : 华清松
 *
 * 选择器案例列表容器
 */
class SelectAdapter(private val list: List<String>) :
    RecyclerView.Adapter<SelectHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(p: Int, data: String)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectHolder {
        return SelectHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_time_picker, null)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SelectHolder, position: Int) {
        holder.setData(list[position])
        holder.itemView.setOnClickListener {
            this.mOnItemClickListener?.onClick(position, list[position])
        }
    }

}