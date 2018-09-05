package com.face.detect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Li Hui
 * @Date 2018/9/5 13:24
 */

@Data
@NoArgsConstructor
public class Threshold {
    private Float millesimal; //千分之一
    private Float tenMillesimal; //万分之一
    private Float hundredMillesimal; //十万分之一
}
