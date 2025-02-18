package qshing.url_analyze.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrlDTO {
    private String url;
    private String ip;
    private String host;
}
