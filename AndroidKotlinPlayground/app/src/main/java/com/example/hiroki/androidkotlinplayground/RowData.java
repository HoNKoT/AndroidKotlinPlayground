package com.example.hiroki.androidkotlinplayground;

import java.util.Random;

public class RowData {
    private final int row;
    private final String title;
    private final String summary;
    private int count = 0;

    RowData(int row) {
        this.row = row;
        this.title = "Row " + (row + 1);
        int random = new Random().nextInt(5);
        if (random % 5 == 0) {
            this.summary = null;
        } else {
            this.summary = "Summary " + row;
        }
    }

    public int getRow() {
        return row;
    }

    public String getTitle() {
        return title + " count " + count;
    }

    public String getSummary() {
        return summary;
    }

    public void increment() {
        count++;
    }
}
