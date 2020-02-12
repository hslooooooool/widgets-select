package vip.qsos.select

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import vip.qsos.lib.select.OnSelectListener
import vip.qsos.lib.select.Operation
import vip.qsos.lib.select.SelectHelper

/**
 * @author : 华清松
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mSelectAdapter: SelectAdapter
    private lateinit var mContext: Context

    private val list = arrayListOf(
        "单选-少量选项",
        "单选-大量选项",
        "多选-少量选项",
        "多选-大量选项&提供全选",
        "多选-限制数量&部分可选"
    )

    private val mOperations0 = arrayListOf(
        Operation("选项1", "1", iconId = R.drawable.dot_1),
        Operation("选项2", "2", iconId = R.drawable.dot_2),
        Operation("选项3", "3", iconId = R.drawable.dot_3),
        Operation("选项4", "4", iconId = R.drawable.dot_4),
        Operation("选项5", "5", iconId = R.drawable.dot_5)
    )

    private val mOperations1 = arrayListOf(
        Operation("选项1", "1"),
        Operation("选项2", "2"),
        Operation("选项3", "3"),
        Operation("选项4", "4"),
        Operation("选项5", "5"),
        Operation("选项6", "6"),
        Operation("选项7", "7"),
        Operation("选项8", "8"),
        Operation("选项9", "9")
    )

    private val mOperations2 = arrayListOf(
        Operation("选项1", "1"),
        Operation("选项2", "2"),
        Operation("选项3", "3"),
        Operation("选项4", "4"),
        Operation("选项5", "5")
    )
    private val mOperations3 = arrayListOf(
        Operation("选项1", "1"),
        Operation("选项2", "2"),
        Operation("选项3", "3"),
        Operation("选项4", "4"),
        Operation("选项5", "5"),
        Operation("选项6", "6"),
        Operation("选项7", "7"),
        Operation("选项8", "8"),
        Operation("选项9", "9")
    )

    private val mOperations4 = arrayListOf(
        Operation("选项1", "1", checked = true, checkable = false),
        Operation("选项2", "2", checked = false, checkable = false),
        Operation("选项3", "3", checked = true, checkable = false),
        Operation("选项4", "4"),
        Operation("选项5", "5"),
        Operation("选项6", "6"),
        Operation("选项7", "7"),
        Operation("选项8", "8"),
        Operation("选项9", "9")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mContext = this
        mSelectAdapter = SelectAdapter(list)
        select_list.layoutManager = LinearLayoutManager(mContext)
        select_list.adapter = mSelectAdapter

        mSelectAdapter.setOnItemClickListener(object : SelectAdapter.OnItemClickListener {
            override fun onClick(p: Int, data: String) {
                when (p) {
                    /**单选*/
                    0 -> {
                        SelectHelper.selectOfSingle(
                            activity = this@MainActivity,
                            operations = mOperations0,
                            listener = object : OnSelectListener<Operation> {
                                override fun select(data: Operation) {
                                    select_picked.text = "${data.value}"
                                }
                            })
                    }
                    /**单选-列表滚动*/
                    1 -> {
                        SelectHelper.selectOfSingle(
                            activity = this@MainActivity,
                            operations = mOperations1,
                            listener = object :
                                OnSelectListener<Operation> {
                                override fun select(data: Operation) {
                                    select_picked.text = "${data.value}"
                                }
                            })
                    }
                    /**多选*/
                    2 -> {
                        SelectHelper.selectOfMultiple(
                            activity = this@MainActivity,
                            title = list[2],
                            limitMax = 3,
                            operations = mOperations2,
                            listener = object :
                                OnSelectListener<List<Operation>> {
                                override fun select(data: List<Operation>) {
                                    select_picked.text = ""
                                    data.forEach { o ->
                                        if (o.checked) {
                                            select_picked.append("${o.value}\t")
                                        }
                                    }
                                }
                            })
                    }
                    /**多选-列表滚动&可全选*/
                    3 -> {
                        SelectHelper.selectOfMultiple(
                            activity = this@MainActivity,
                            title = list[3],
                            limitMax = 0,
                            operations = mOperations3,
                            listener = object :
                                OnSelectListener<List<Operation>> {
                                override fun select(data: List<Operation>) {
                                    select_picked.text = ""
                                    data.forEach { o ->
                                        if (o.checked) {
                                            select_picked.append("${o.value}\t")
                                        }
                                    }
                                }
                            })
                    }
                    /**多选-限制数量&部分可选*/
                    4 -> {
                        SelectHelper.selectOfMultiple(
                            activity = this@MainActivity,
                            title = list[4],
                            limitMax = 4,
                            operations = mOperations4,
                            listener = object :
                                OnSelectListener<List<Operation>> {
                                override fun select(data: List<Operation>) {
                                    select_picked.text = ""
                                    data.forEach { o ->
                                        if (o.checked) {
                                            select_picked.append("${o.value}\t")
                                        }
                                    }
                                }
                            })
                    }
                }
            }
        })
    }
}