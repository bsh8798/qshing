package qshing.url_analyze.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import qshing.url_analyze.dto.UrlDTO;
import java.net.InetAddress;
import java.net.URL;

@Component
@Service
public class ExtractInfoService {

    public UrlDTO extractAndSet(String inputUrl) {
        UrlDTO urlDTO = new UrlDTO();
        try {
            URL url = new URL(inputUrl);
            String host = url.getHost();

            InetAddress address = InetAddress.getByName(host);
            String ip = address.getHostAddress();

            urlDTO.setUrl(inputUrl);
            urlDTO.setIp(ip);
            urlDTO.setHost(host);
            return urlDTO;

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
