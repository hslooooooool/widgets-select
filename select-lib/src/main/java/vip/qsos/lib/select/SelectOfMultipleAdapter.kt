package vip.qsos.lib.select

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chose_multiple.view.*

/**
 * @author 华清松
 *
 * 多选列表容器
 * @param data 可选列表
 * @param checkedNum 已选数量
 * @param limitMax 最多可选限制，传<0表示不限制
 */
class SelectOfMultipleAdapter constructor(
    context: Context,
    private val data: List<Operation>,
    var checkedNum: Int,
    private val limitMax: Int = 0
) : RecyclerView.Adapter<SelectOfMultipleAdapter.ViewHolder>() {

    private val inflate: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflate.inflate(R.layout.item_chose_multiple, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.text = data[position].key
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = data[position].checked
        holder.checkBox.isEnabled = data[position].checkable

        holder.checkBox.setOnCheckedChangeListener { v, isChecked ->
            data[position].checked = isChecked
            if (isChecked) checkedNum++ else checkedNum--

            if (limitMax in 1 until checkedNum) {
                /**已达到最大可选数量,屏蔽所有未选项按钮点击*/
                Toast.makeText(holder.itemView.context, "已达最大可选限制【$limitMax】", Toast.LENGTH_SHORT)
                    .show()
                v.isChecked = false
                data[position].checked = false
                checkedNum--
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox: CheckBox = itemView.chose_multiple_cb
    }

}
