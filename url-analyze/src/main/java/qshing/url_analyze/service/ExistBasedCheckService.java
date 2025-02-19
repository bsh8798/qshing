package qshing.url_analyze.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Service
public class ExistBasedCheckService {

    public boolean isExistenceDanger(String url) {
        String[] extension = {".php", ".html", ".htm", ".hwp", ".hwpx", ".pptx", ".docx",
                ".iso", ".js", ".lnk", ".vbs", ".xls", ".xml", ".zip", ".xlsx"};

        if(!url.contains("https")) {  //https가 아닌 경우
            System.out.println("https 아님");
            return true;
        }
        if(url.contains("mailto:")) {  //mailto:를 포함하는 경우
            System.out.println("mailto: 포함");
            return true;
        }
        if(containIP(url)) {  //ip 주소가 포함된 경우
            System.out.println("ip 주소 포함");
            return true;
        }
        for(String ex : extension) {  //확장자가 포함된 경우
            if(url.contains(ex)) {
                System.out.println("확장자 포함");
                return true;
            }
        }
        return false;
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
