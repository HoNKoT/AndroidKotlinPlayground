package com.example.hiroki.androidkotlinplayground

import java.util.Random

class RowData internal constructor(val row: Int) {
    private val title: String
    val summary: String?
    private var count = 0

    init {
        this.title = "Row " + (row + 1)
        val random = Random().nextInt(5)
        if (random % 5 == 0) {
            this.summary = null
        } else {
            this.summary = "Summary " + row
        }
    }

    fun getTitle(): String {
        return title + " count " + count
    }

    fun increment() {
        count++
    }
}
