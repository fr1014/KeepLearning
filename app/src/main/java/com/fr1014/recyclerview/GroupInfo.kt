package com.fr1014.recyclerview

/**
 * Create by fanrui07
 * Date: 2022/5/16
 * Describe:
 */
data class GroupInfo(val groupId: Int, val title: String, val index : Int)

fun GroupInfo.isFirstViewInGroup(): Boolean {
    return index == 0
}
