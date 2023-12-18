package com.FooFa.FooFa_Server.dto;

import com.FooFa.FooFa_Server.domain.Memo;
import com.FooFa.FooFa_Server.domain.Spot;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotDto {
    private String spotid;

    private int countMemo = 0;

    public Spot toEntity(){
        return Spot.builder()
                .spotid(spotid)
                .countMemo(countMemo)
                .build();
    }
}
