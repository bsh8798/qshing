package qshing.url_analyze.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import qshing.url_analyze.service.UrlAnalysisService;

@Component
@Controller
@RequiredArgsConstructor
public class UrlController {
    private final UrlAnalysisService urlAnalysisService;

    public void analyzeURL(String url) {
        //url 객체 생성
        urlAnalysisService.setUrlDTO(url);

        //url이 안전하다고 판단된 경우 result = false
        boolean result = urlAnalysisService.checkDangerous();
    }
}
