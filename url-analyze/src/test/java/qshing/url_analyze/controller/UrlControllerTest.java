/*package qshing.url_analyze.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qshing.url_analyze.dto.UrlDTO;
import qshing.url_analyze.repository.RepositoryTmp;
import qshing.url_analyze.service.ExistBasedCheckService;
import qshing.url_analyze.service.ExtractInfoService;
import qshing.url_analyze.service.UrlAnalysisService;

class UrlControllerTest {

    RepositoryTmp repositoryTmp;
    ExtractInfoService extractInfoService;
    ExistBasedCheckService existBasedCheckService;
    UrlAnalysisService urlAnalysisService;
    UrlController urlController;

    @BeforeEach
    void beforeEach() {
        repositoryTmp = new RepositoryTmp();
        extractInfoService = new ExtractInfoService();
        existBasedCheckService = new ExistBasedCheckService();
        urlAnalysisService = new UrlAnalysisService(repositoryTmp, extractInfoService, existBasedCheckService);
        urlController = new UrlController(urlAnalysisService);
    }

    @Test
    void analysis() {
        UrlDTO urlDTONaver = new UrlDTO();
        urlDTONaver.setUrl("https://www.naver.com/");
        urlDTONaver.setIp("192.168.0.1");
        urlDTONaver.setHost("www.naver.com");
        repositoryTmp.WhiteSave(urlDTONaver);

        urlController.analyzeURL("https://www.naver.com/");
        urlController.analyzeURL("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EB%84%A4%EC%9D%B4%EB%B2%84+ip%EC%A3%BC%EC%86%8C");
        urlController.analyzeURL("https://www.google.com/search?q=%EC%9E%90%EB%B0%94+url%EC%9D%98+ip+%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0&sca_esv=e06434e0b22fe8e9&sxsrf=AHTn8zopNcHVo_8aCQnujyaLgGUHY-B60A%3A1739711550067&ei=PuSxZ67qA_bW1e8P7vfx4AI&ved=0ahUKEwiugdDHosiLAxV2a_UHHe57HCwQ4dUDCBA&uact=5&oq=%EC%9E%90%EB%B0%94+url%EC%9D%98+ip+%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0&gs_lp=Egxnd3Mtd2l6LXNlcnAiHeyekOuwlCB1cmzsnZggaXAg6rCA7KC47Jik6riwMggQIRigARjDBEjkGVCjBFjUF3ABeAGQAQCYAZICoAGYEqoBBTAuNS42uAEDyAEA-AEBmAIFoAKbBsICChAAGLADGNYEGEfCAgYQABgHGB7CAgUQABiABMICBhAAGAgYHsICBRAAGO8FwgIIEAAYgAQYogSYAwCIBgGQBgqSBwUxLjMuMaAH7h8&sclient=gws-wiz-serp");
        urlController.analyzeURL("http://naver.com");
        urlController.analyzeURL("https://naver.com/127.0.0.1");
        urlController.analyzeURL("https://naver.com/mailto:hehe");
        urlController.analyzeURL("https://naver.com/hehe.zip");
    }

}*/