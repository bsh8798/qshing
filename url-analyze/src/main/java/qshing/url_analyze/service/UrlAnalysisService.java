package qshing.url_analyze.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import qshing.url_analyze.dto.UrlDTO;
import qshing.url_analyze.repository.RepositoryTmp;

@Component
@Service
@RequiredArgsConstructor
public class UrlAnalysisService {

    private final RepositoryTmp repositoryTmp;
    private final ExtractInfoService extractInfoService;
    private final ExistBasedCheckService existBasedCheckService;
    private UrlDTO urlDTO;

    public void setUrlDTO(String url) {
        this.urlDTO = extractInfoService.extractAndSet(url);
    }

    public boolean findByUrlWhite(String url) {
        return repositoryTmp.findByUrlWhite(url).isPresent();  //없으면 false 반환
    }

    public boolean findByUrlBlack(String url) {
        return repositoryTmp.findByUrlBlack(url).isPresent();
    }

    public boolean checkDangerous() {
        boolean isDangerous = false;

        if(findByUrlWhite(urlDTO.getUrl())) {  //화이트리스트에 존재하는 url인 경우
            System.out.println("화이트리스트에 존재");
            return false;
        }
        else if(findByUrlBlack(urlDTO.getUrl())) {  //블랙리스트에 존재하는 url인 경우
            System.out.println("블랙리스트에 존재");
            return true;
        }
        else {  //데이터베이스에 없는 url인 경우
            System.out.println("데이터베이스에 없음");

            //존재 기반 - 조건에 걸리면 true / 안 걸리면 false
            isDangerous = existBasedCheckService.isExistenceDanger(urlDTO.getUrl());
            if(!isDangerous) {

            }

            return isDangerous;
        }
    }
}
