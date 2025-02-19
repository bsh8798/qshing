package qshing.url_analyze.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrlDTO {
    private String url;
    private boolean isPossibleDNS;
    private String host;
}
