package vip.qsos.lib.select

/**
 * @author 华清松
 *
 * 选择器回调接口
 */
interface OnSelectListener<T> {

    fun select(data: T)

}