package com.face.detect.service;

import com.alibaba.fastjson.JSON;
import com.face.detect.component.RestApiClient;
import com.face.detect.constants.ImgTypeConstants;
import com.face.detect.domain.ComparationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author Li Hui
 * @Date 2018/9/5 10:13
 */

@Service
@Slf4j
public class FaceDetectionService {

    @Autowired
    private RestApiClient restApiClient;

    public float getMatchRate(String image1, String image2) {
        String response = restApiClient.compareImages(image1, image2,
                ImgTypeConstants.TYPE_IMG_URL);

        if (StringUtils.isEmpty(response)) {
            log.error("no response");
            return 0F;
        }

        response = response.replace("1e-3", "millesimal");
        response = response.replace("1e-4", "tenMillesimal");
        response = response.replace("1e-5", "hundredMillesimal");


        ComparationResponse comparationResponse = JSON.parseObject(response, ComparationResponse.class);
        if (null != comparationResponse.getConfidence()) {
            log.info("匹配度： {}", comparationResponse.getConfidence());
            return comparationResponse.getConfidence();
        }

        return 0F;
    }
}
