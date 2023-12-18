package com.FooFa.FooFa_Server.dto;

import com.FooFa.FooFa_Server.domain.Memo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {
    private String memoid;

    private String textmemo;

    private List<String> imgurl;

    private double lat;

    private double lng;

    private String spotid;
    public Memo toEntity(){
        return Memo.builder()
                .memoid(memoid)
                .textmemo(textmemo)
                .imgurl(imgurl)
                .lat(lat)
                .lng(lng)
                .spotid(spotid)
                .build();
    }

    public MemoDto fromEntity (Memo memo){
        return MemoDto.builder()
                .memoid(memo.getMemoid())
                .textmemo(memo.getTextmemo())
                .imgurl(memo.getImgurl())
                .lat(memo.getLat())
                .lng(memo.getLng())
                .spotid(memo.getSpotid())
                .build();
    }
}
