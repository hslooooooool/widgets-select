package vip.qsos.lib.select

import androidx.annotation.DrawableRes

/**
 * @author 华清松
 *
 * 选项实体
 * @param key 选项名称
 * @param value 选项值
 * @param checked 是否选中
 * @param checkable 是否可选
 * @param iconId 选项图标
 */
data class Operation(
    val key: String,
    val value: Any = key,
    var checked: Boolean = false,
    var checkable: Boolean = true,
    @DrawableRes
    var iconId: Int? = null
)