package qshing.url_analyze.service;

import org.apache.commons.net.whois.WhoisClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import qshing.url_analyze.dto.UrlDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Service
public class DateBasedCheckService {

    public boolean isExistenceDanger(UrlDTO urlDTO) {
        WhoisClient whoisClient = new WhoisClient();
        LocalDate now = LocalDate.now();
        String currentDate = now.toString();
        int currentYear = Integer.parseInt(currentDate.substring(0, 4));
        int currentMonth = Integer.parseInt(currentDate.substring(5, 7));
        int currentDay = Integer.parseInt(currentDate.substring(8, 10));

        try {
            //WHOIS 서버 연결
            whoisClient.connect(whoisClient.DEFAULT_HOST);

            //WHOIS 데이터 조회
            String whoisData = whoisClient.query(urlDTO.getHost());

            //생성일자 추출
            String creationDate = "Creation Date: (.+)";  //Creation Date: 문자열 뒤에 나오는 하나 이상의 문자를 캡쳐
            Matcher creationMatcher = Pattern.compile(creationDate).matcher(whoisData);
            if(creationMatcher.find()) {
                //최근에 생성된 도메인인 경우, 위험 판단
                if(isRecently(creationMatcher.group(1), currentYear, currentMonth, currentDay)) {
                    return true;
                }
            }

            //갱신일자 추출
            String updateDate = "Updated Date: (.+)";
            Matcher updateMatcher = Pattern.compile(updateDate).matcher(whoisData);
            if(updateMatcher.find()) {
                //최근에 갱신된 도메인인 경우, 위험 판단
                if(isRecently(updateMatcher.group(1), currentYear, currentMonth, currentDay)) {
                    return true;
                }
            }

            //만료일자 추출
            String expirationDate = "Expiration Date: (.+)";
            Matcher expireMatcher = Pattern.compile(expirationDate).matcher(whoisData);
            if(expireMatcher.find()) {
                //만료일자가 얼마 남지 않은 경우, 위험 판단
                if(isRecently(expireMatcher.group(1), currentYear, currentMonth, currentDay)) {
                    return true;
                }
            }

            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isRecently(String date, int currentYear, int currentMonth, int currentDay) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        if(year == currentYear && month == currentMonth) {
            if(Math.abs(currentDay - day) <= 7) {
                // 날짜가 현재 날짜와 일주일 이내로 차이가 나는 경우
                return true;
            }
        }
        return false;
    }
}
