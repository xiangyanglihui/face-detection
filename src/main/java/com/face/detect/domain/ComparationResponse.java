package com.face.detect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Li Hui
 * @Date 2018/9/5 10:14
 */

@Data
@NoArgsConstructor
public class ComparationResponse {
    private String request_id;
    private Float confidence;
    private String thresholds;
    private String image_id1;
    private String image_id2;
    private int time_used;
    private String error_message;
}
