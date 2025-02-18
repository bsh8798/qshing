package qshing.url_analyze.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import qshing.url_analyze.dto.UrlDTO;
import qshing.url_analyze.entity.WhiteUrl;
import java.util.Optional;

@Component
@Repository
public interface WhiteUrlRepository extends JpaRepository<WhiteUrl, String> {
    @Query("SELECT new qshing.url_analyze.dto.UrlDTO(b.url, b.ip, b.host) FROM WhiteUrl b WHERE b.url = :url")
    Optional<UrlDTO> findByUrl(@Param("url") String url);
}
