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
        urlAnalysisService.setUrlDTO(url);
        urlAnalysisService.compareDataBase();
        //urlAnalysisService.isExistenceDanger();
    }
}
