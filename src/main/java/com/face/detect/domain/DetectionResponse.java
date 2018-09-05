package com.face.detect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Li Hui
 * @Date 2018/9/5 14:56
 */

@Data
@NoArgsConstructor
public class DetectionResponse {
    private String request_id;
    private List<DetectFace> faces;
    private String image_id;
    private int time_used;
    private String error_message;
}
