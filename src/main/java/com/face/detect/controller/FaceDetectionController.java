package com.face.detect.controller;

import com.face.detect.domain.ComparationRequest;
import com.face.detect.service.FaceDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Li Hui
 * @Date 2018/9/5 10:05
 */

@RestController
@RequestMapping(value = "/face")
public class FaceDetectionController {

    @Autowired
    private FaceDetectionService faceDetectionService;

    @ResponseBody
    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public String compareToken(@RequestBody ComparationRequest comparationRequest) {
        float confidence = faceDetectionService.getMatchRate(comparationRequest.getImage1(), comparationRequest.getImage2());
        return "匹配程度：" + confidence;
    }
}
