package com.sby1027.px.tools

import java.io.File

fun main() {

    val filePath = "/Users/bytedance/Downloads/logcat.log"
    val filter00 = "VoyahActivityCallbacks|VoyahH37LimitMonitor|vmPlayer.isDrivingLimitedEvent|PlayDriveLimit"

    val tags = filter00.split("|")

    val printSet = arrayListOf<(String) -> Unit>(
        { println("\u001b[31m$it\u001b[0m") }, // 红色1
        { println("\u001b[34m$it\u001b[0m") }, // 蓝色4
        { println("\u001b[35m$it\u001b[0m") }, // 紫色5
        { println("\u001b[33m$it\u001b[0m") }, // 黄色3
        { println("\u001b[36m$it\u001b[0m") }, // 青色6
        { println("\u001b[32m$it\u001b[0m") }, // 绿色2
        { println("\u001b[37m$it\u001b[0m") }, // 白色7
    )
    val nSize = printSet.size
    val iMap = HashMap<String, (String) -> Unit>()
    var n = 0
    tags.forEach {
        iMap[it] = printSet[n % nSize]
        ++n
    }

    File(filePath).forEachLine {
        tags.forEach { tag ->
            if (it.contains(tag)) {
                iMap[tag]?.invoke(it)
            }
        }
    }
}








