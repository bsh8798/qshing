package qshing.url_analyze.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import qshing.url_analyze.dto.UrlDTO;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

@Component
@Service
public class ExtractInfoService {

    public UrlDTO extractAndSet(String inputUrl) {
        UrlDTO urlDTO = new UrlDTO();
        try {
            URL url = new URL(inputUrl);
            String host = url.getHost();

            try {
                InetAddress address = InetAddress.getByName(host);
                urlDTO.setPossibleDNS(true);
            } catch(UnknownHostException e) {
                urlDTO.setPossibleDNS(false);
            }

            urlDTO.setUrl(inputUrl);
            return urlDTO;

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
