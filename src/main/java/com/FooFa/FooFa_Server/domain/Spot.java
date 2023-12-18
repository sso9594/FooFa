package com.FooFa.FooFa_Server.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "spot")
public class Spot {
    @Id
    private String spotid;

    private int countMemo = 0;
}
