package qshing.url_analyze.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String analyzeURL(@RequestBody RequestDTO request, Model model) {
        //요청에서 url 추출
        String url = request.getText();
        model.addAttribute("url", url);
        // displayUrl.html 템플릿을 반환
        return "displayUrl";
        //url 객체 생성
        /*urlAnalysisService.setUrlDTO(url);

        //url이 안전하다고 판단된 경우 result = false
        boolean result = urlAnalysisService.checkDangerous();*/
    }
}
