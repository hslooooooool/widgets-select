package vip.qsos.lib.select

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author 华清松
 */
object SelectHelper {

    /**单选
     *
     * @param operations 可选项
     * @param listener 选择结果回调-返回单选项*/
    fun selectOfSingle(
        activity: AppCompatActivity,
        operations: List<Operation>,
        listener: OnSelectListener<Operation>
    ) {
        val bottomDialog = BottomDialog()
        bottomDialog.setFragmentManager(activity.supportFragmentManager)
        bottomDialog.setLayoutRes(R.layout.dialog_single)
        bottomDialog.setDimAmount(0.6f)
        bottomDialog.setViewListener(object : BottomDialog.ViewListener {
            override fun bindView(dialog: AbsBottomDialog) {
                val operationRv = dialog.findViewById<RecyclerView>(R.id.operation_rv)
                operationRv.layoutManager = LinearLayoutManager(activity)
                val operationAdapter =
                    SelectOfSingleAdapter(activity, operations)
                operationRv.adapter = operationAdapter

                operationAdapter.setOnItemClickListener(object : OnSelectListener<Operation> {
                    override fun select(data: Operation) {
                        listener.select(data)
                        bottomDialog.dismiss()
                    }
                })
            }
        })
        bottomDialog.show()
    }

    /**多选
     *
     * @param title 弹窗标题
     * @param limitMax 最大可选数，传<0表示不限制
     * @param operations 可选项
     * @param listener 选择结果回调-返回所有选项*/
    fun selectOfMultiple(
        activity: AppCompatActivity,
        title: String? = "多选",
        limitMax: Int = 0,
        operations: List<Operation>,
        listener: OnSelectListener<List<Operation>>
    ) {
        val bottomDialog = BottomDialog()
        bottomDialog.setFragmentManager(activity.supportFragmentManager)
        bottomDialog.setLayoutRes(R.layout.dialog_multiple)
        bottomDialog.setDimAmount(0.6f)
        bottomDialog.setViewListener(object : BottomDialog.ViewListener {
            override fun bindView(dialog: AbsBottomDialog) {
                val screen = dialog.findViewById<RecyclerView>(R.id.rv_select)
                val tvTitle = dialog.findViewById<TextView>(R.id.tv_select_title)
                val tvAll = dialog.findViewById<TextView>(R.id.tv_select_all)
                val tvClear = dialog.findViewById<TextView>(R.id.tv_select_clear)
                val tvSure = dialog.findViewById<TextView>(R.id.tv_select_sure)
                var checkedNum = 0
                operations.forEach {
                    if (it.checked) checkedNum++
                }
                val adapter = SelectOfMultipleAdapter(
                    activity,
                    operations,
                    checkedNum,
                    limitMax
                )
                screen.layoutManager = LinearLayoutManager(activity)
                screen.adapter = adapter
                tvTitle.text = "$title"
                /**在不做数量限制时，提供全选功能*/
                if (limitMax > 0 && limitMax < operations.size) {
                    tvAll.visibility = View.GONE
                } else {
                    tvAll.visibility = View.VISIBLE
                }
                tvClear.setOnClickListener {
                    operations.forEach {
                        if (it.checkable && it.checked) {
                            adapter.checkedNum--
                            it.checked = false
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
                tvAll.setOnClickListener {
                    operations.forEach {
                        if (it.checkable && !it.checked) {
                            adapter.checkedNum++
                            it.checked = true
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
                tvSure.setOnClickListener {
                    listener.select(operations)
                    bottomDialog.dismiss()
                }
            }
        })
        bottomDialog.show()
    }

}