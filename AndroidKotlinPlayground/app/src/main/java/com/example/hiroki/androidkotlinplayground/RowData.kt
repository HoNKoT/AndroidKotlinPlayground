package com.example.hiroki.androidkotlinplayground

import java.util.*

class RowData internal constructor(private val row: Int) {

    val summary: String? =
            if (Random().nextInt(5) == 0) null
            else "Summary " + row

    private var count = 0

    fun getTitle(): String = "Row " + (row + 1) + " count " + count

    fun increment() = count++
}
