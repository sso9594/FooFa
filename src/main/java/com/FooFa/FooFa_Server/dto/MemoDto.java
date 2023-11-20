package com.FooFa.FooFa_Server.dto;

import com.FooFa.FooFa_Server.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {
    private String memoid;

    private String textmemo;

    private String imgurl;

    private double lat;

    private double lng;

    public MemoDto fromEntity (Memo memo){
        return MemoDto.builder()
                .memoid(memo.getMemoid())
                .textmemo(memo.getTextmemo())
                .imgurl(memo.getImgurl())
                .lat(memo.getLat())
                .lng(memo.getLng())
                .build();
    }
}
