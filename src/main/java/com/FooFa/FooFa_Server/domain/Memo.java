package com.FooFa.FooFa_Server.domain;

import com.FooFa.FooFa_Server.dto.MemoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "memo")
public class Memo {
    @Id
    private String memoid;

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

    public Memo update(MemoDto memoDto){
        this.memoid = memoDto.getMemoid();
        this.textmemo = memoDto.getTextmemo();
        this.imgurl = memoDto.getImgurl();
        this.lat = memoDto.getLat();
        this.lng = memoDto.getLng();

        return this;
    }

}
