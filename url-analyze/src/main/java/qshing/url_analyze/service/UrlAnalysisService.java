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
    private final DateBasedCheckService dateBasedCheckService;
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

    /*
    위험하다고 판단된 경우 - true 반환
    안전하다고 판단된 경우 - false 반환
        1. 데이터베이스와 비교
        2. 존재 기반
        3. 비정상적
        4. 일수 기반
        5.
     */
    public boolean checkDangerous() {

        if(findByUrlWhite(urlDTO.getUrl())) {  //화이트리스트에 존재하는 url인 경우
            return false;
        }
        else if(findByUrlBlack(urlDTO.getUrl())) {  //블랙리스트에 존재하는 url인 경우
            return true;
        }
        else {  //데이터베이스에 없는 url인 경우 = 다른 경우 확인 필요

            //존재 기반 - 조건에 걸리면 true / 안 걸리면 false
            if(existBasedCheckService.isExistenceDanger(urlDTO.getUrl())) {  //조건에 걸림
                repositoryTmp.BlackSave(urlDTO);
                return true;
            }
            else {
                //비정상적
                if(!urlDTO.isPossibleDNS()) {  //DNS 질의가 불가능한 경우
                    repositoryTmp.BlackSave(urlDTO);
                    return true;
                }
                else {  //DNS 질의가 가능한 경우 = 다른 경우 확인 필요

                    //일수 기반 - 최근 생성/갱신 or 만료 일자가 얼마 안 남음
                    if(dateBasedCheckService.isExistenceDanger(urlDTO)) {  //일수 조건에 걸린 경우
                        repositoryTmp.BlackSave(urlDTO);
                        return true;
                    }
                    else {  //아무 조건에도 걸리지 않은 경우 - 안전하다고 판단
                        repositoryTmp.WhiteSave(urlDTO);
                        return false;
                    }
                }
            }
        }
    }
}
