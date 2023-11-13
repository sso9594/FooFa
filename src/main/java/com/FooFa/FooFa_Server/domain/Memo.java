package com.FooFa.FooFa_Server.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "memo")
public class Memo {
    @Id
    private Long memoid;

    private String textmemo;

    private String imgurl;

    private double lat;

    private double lng;

    @Builder
    public Memo(Long memoid, String textmemo, String imgurl, double lat, double lng) {
        this.textmemo = textmemo;
        this.imgurl = imgurl;
        this.lat = lat;
        this.lng = lng;
    }

}
