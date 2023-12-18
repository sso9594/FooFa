package com.FooFa.FooFa_Server.dto;

import com.FooFa.FooFa_Server.domain.Memo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoResponse {
    private String memoid;

    private String textmemo;

    private List<byte[]> imgurl;

    private double lat;

    private double lng;

    private String spotid;

    public MemoResponse fromEntity (Memo memo, List<byte[]> imgurl){
        return MemoResponse.builder()
                .memoid(memo.getMemoid())
                .textmemo(memo.getTextmemo())
                .imgurl(imgurl)
                .lat(memo.getLat())
                .lng(memo.getLng())
                .build();
    }
}
