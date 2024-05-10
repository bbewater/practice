package com.sz.bewater.practice.common;

import lombok.Data;

@Data
public class Interval {

    int start;
    int end;
    String isValid;

    Interval(int start, int end,String isValid) {
        this.start = start;
        this.end = end;
        this.isValid = isValid;
    }

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
        this.isValid = ""; // 空状态表示新添加的区间
    }

    // 检查当前区间是否完全包含另一个区间
    boolean contains(Interval other) {
        return this.start <= other.start && this.end >= other.end;
    }

    // 检查当前区间是否与另一个区间有交集
    boolean intersects(Interval other) {
        return this.start < other.end && this.end > other.start;
    }
}
