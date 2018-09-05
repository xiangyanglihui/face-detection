package com.face.detect.component;

import com.face.detect.constants.ImgTypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @Author Li Hui
 * @Date 2018/9/5 10:19
 * face++ API图片匹配文档路径：https://console.faceplusplus.com.cn/documents/4887586
 */

@Component
@Slf4j
public class RestApiClient {

    private String detectUrl;
    private String compareUrl;

    @Value("${faceplusplus.api.key}")
    private String apiKey;

    @Value("${faceplusplus.api.secret}")
    private String apiSecret;

    private RestTemplate restTemplate;

    @PostConstruct
    public void createRestApiClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        restTemplate = new RestTemplate(factory);

        compareUrl = "https://api-cn.faceplusplus.com/facepp/v3/compare?api_key=" + apiKey + "&api_secret=" + apiSecret;
        detectUrl = "https://api-cn.faceplusplus.com/facepp/v3/detect?api_key=" + apiKey + "&api_secret=" + apiSecret;
    }

    public String compareImages(String image1, String image2, int type) {
        String url = compareUrl;
        if (type == ImgTypeConstants.TYPE_FACE_TOKEN) {
            url += ("&face_token1=" + image1 + "&face_token2=" + image2);
        } else if (type == ImgTypeConstants.TYPE_IMG_URL) {
            url += ("&image_url1=" + image1 + "&image_url2=" + image2);
        } else if (type == ImgTypeConstants.TYPE_IMG_FILE) {
            url += ("&image_file1=" + image1 + "&image_file2=" + image2);
        } else if (type == ImgTypeConstants.TYPE_IMG_BASE64) {
            url += ("&image_base64_1=" + image1 + "&image_base64_2=" + image2);
        } else {
            log.error("不认识的图片类型");
            return null;
        }
        ResponseEntity<String> response = restTemplate.postForEntity(url, null,
                String.class);

        if (null != response) {
            return response.getBody();
        }
        return null;
    }

    public String detectFace(String image, int type) {
        String url = detectUrl;
        if (type == ImgTypeConstants.TYPE_IMG_URL) {
            url += ("&image_url=" + image + "&return_landmark=2");
        } else if (type == ImgTypeConstants.TYPE_IMG_FILE) {
            url += ("&image_file=" + image + "&return_landmark=1");
        } else if (type == ImgTypeConstants.TYPE_IMG_BASE64) {
            url += ("&image_base64=" + image + "&return_landmark=1");
        } else {
            log.error("不认识的类型");
            return null;
        }

        ResponseEntity<String> response = restTemplate.postForEntity(url, null,
                String.class);
        if (null != response) {
            return response.getBody();
        }
        return null;
    }
}
