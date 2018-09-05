package com.face.detect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Li Hui
 * @Date 2018/9/5 13:40
 */


@Data
@NoArgsConstructor
public class ComparationRequest {
    private String image1;
    private String image2;
}
