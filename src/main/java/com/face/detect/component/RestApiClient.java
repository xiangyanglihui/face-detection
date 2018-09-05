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
 */

@Component
@Slf4j
public class RestApiClient {

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
    }

    public String compareImages(String image1, String image2, int type) {
        if (type == ImgTypeConstants.TYPE_FACE_TOKEN) {
            compareUrl += ("&face_token1=" + image1 + "&face_token12=" + image2);
        } else if (type == ImgTypeConstants.TYPE_IMG_URL) {
            compareUrl += ("&image_url1=" + image1 + "&image_url2=" + image2);
        } else if (type == ImgTypeConstants.TYPE_IMG_FILE) {
            compareUrl += ("&image_file1=" + image1 + "&image_file2=" + image2);
        } else if (type == ImgTypeConstants.TYPE_IMG_BASE64) {
            compareUrl += ("&image_base64_1=" + image1 + "&image_base64_2=" + image2);
        } else {
            log.error("不认识的图片类型");
            return null;
        }
        ResponseEntity<String> response = restTemplate.postForEntity(compareUrl, null,
                String.class);

        if (null != response) {
            return response.getBody();
        }
        return null;
    }
}
