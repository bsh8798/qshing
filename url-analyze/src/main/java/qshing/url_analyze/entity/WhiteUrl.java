package qshing.url_analyze.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "white_list")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhiteUrl {
    @Id
    private String url;

    private String ip;

    private String host;
}
