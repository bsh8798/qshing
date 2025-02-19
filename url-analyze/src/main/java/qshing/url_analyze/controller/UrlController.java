package qshing.url_analyze.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qshing.url_analyze.dto.RequestDTO;
import qshing.url_analyze.service.UrlAnalysisService;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlAnalysisService urlAnalysisService;

    @PostMapping("/analyze")
    public boolean analyzeURL(@RequestBody RequestDTO request) {
        //요청에서 url 추출
        String url = request.getText();
        //System.out.println(url);

        //url 객체 생성
        urlAnalysisService.setUrlDTO(url);

        //url이 안전하다고 판단된 경우 result = false
        boolean result = urlAnalysisService.checkDangerous();
        //System.out.println(result);
        return result;
    }
}
