package com.sz.bewater.practice.common;

import lombok.Data;

@Data
public class IntervalNew {
    int start;
    int end;
    String status;
    Integer count = 0;

    public IntervalNew(int start, int end, String status) {
        this.start = start;
        this.end = end;
        this.status = status;
    }
    public IntervalNew(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + ",\"" + status + "\"" +","+count+"\")";
    }
}
