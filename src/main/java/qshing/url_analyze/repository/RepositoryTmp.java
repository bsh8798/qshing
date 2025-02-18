package qshing.url_analyze.repository;

import org.springframework.stereotype.Component;
import qshing.url_analyze.dto.UrlDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class RepositoryTmp {

    private static Map<String, UrlDTO> whiteStore = new HashMap<>();
    private static Map<String, UrlDTO> blackStore = new HashMap<>();

    public void WhiteSave(UrlDTO urlDTO) {
        whiteStore.put(urlDTO.getUrl(), urlDTO);
    }
    public void BlackSave(UrlDTO urlDTO) {
        blackStore.put(urlDTO.getUrl(), urlDTO);
    }
    public Optional<UrlDTO> findByUrlWhite(String url) {
        return Optional.ofNullable(whiteStore.get(url));
    }
    public Optional<UrlDTO> findByUrlBlack(String url) {
        return Optional.ofNullable(blackStore.get(url));
    }
}
