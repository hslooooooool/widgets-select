package vip.qsos.lib.select

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @author 华清松
 *
 * 单选列表容器
 */
class SelectOfSingleAdapter internal constructor(
    private val mContext: Context,
    private val operations: List<Operation> = arrayListOf()
) : RecyclerView.Adapter<SelectOfSingleAdapter.ViewHolder>() {

    private var onSelectListener: OnSelectListener<Operation>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_operation, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.operationTv.tag = position
        viewHolder.operationTv.text = operations[position].key

        val drawable = ContextCompat.getDrawable(
            mContext, operations[position].iconId ?: R.drawable.dot_1
        )

        if (drawable != null) {
            // 必须设置图片大小，否则不显示
            drawable.setBounds(0, 0, 40, 40)
            viewHolder.operationTv.setCompoundDrawables(drawable, null, null, null)
        }
        viewHolder.operationTv.isClickable = operations[position].checkable
        viewHolder.operationTv.setOnClickListener {
            onSelectListener?.select(operations[position])
        }
    }

    override fun getItemCount(): Int {
        return operations.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val operationTv: TextView = itemView.findViewById(R.id.operation_tv)
    }

    fun setOnItemClickListener(onTListener: OnSelectListener<Operation>) {
        this.onSelectListener = onTListener
    }
}