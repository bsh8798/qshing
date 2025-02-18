package qshing.url_analyze.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import qshing.url_analyze.dto.UrlDTO;
import qshing.url_analyze.repository.RepositoryTmp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Service
@RequiredArgsConstructor
public class UrlAnalysisService {

    private final RepositoryTmp repositoryTmp;
    //private final WhiteUrlRepository whiteUrlRepository;
    //private final BlackUrlRepository blackUrlRepository;
    private final ExtractInfoService extractInfoService;
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

    public void compareDataBase() {
        if(findByUrlWhite(urlDTO.getUrl())) {  //화이트리스트에 존재하는 url인 경우
            System.out.println("화이트리스트에 존재");
        }
        else if(findByUrlBlack(urlDTO.getUrl())) {  //블랙리스트에 존재하는 url인 경우
            System.out.println("블랙리스트에 존재");
        }
        else {  //데이터베이스에 없는 url인 경우
            System.out.println("데이터베이스에 없음");
            isExistenceDanger();
        }
    }

    /*
    존재 기반 - 하나만 걸려도 악성
     */
    public void isExistenceDanger() {
        String url = urlDTO.getUrl();
        String[] extension = {".php", ".html", ".htm", ".hwp", ".hwpx", ".pptx", ".docx",
                ".iso", ".js", ".lnk", ".vbs", ".xls", ".xml", ".zip", ".xlsx"};

        if(!url.contains("https")) {  //https가 아닌 경우
            System.out.println("https 아님");

        }
        if(url.contains("mailto:")) {  //mailto:를 포함하는 경우
            System.out.println("mailto: 포함");

        }
        if(containIP(url)) {  //ip 주소가 포함된 경우
            System.out.println("ip 주소 포함");

        }
        for(String ex : extension) {  //확장자가 포함된 경우
            if(url.contains(ex)) {
                System.out.println("확장자 포함");

            }
        }
    }
    public static boolean containIP(String url) {
        String IPV4_REG = "(?:\\d{1,3}\\.){3}\\d{1,3}";
        String IPV6_REG = "(?:[0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})";

        Pattern pattern4 = Pattern.compile(IPV4_REG);
        Matcher matcher4 = pattern4.matcher(url);

        Pattern pattern6 = Pattern.compile(IPV6_REG);
        Matcher matcher6 = pattern6.matcher(url);

        return matcher4.find() || matcher6.find();
    }
}
