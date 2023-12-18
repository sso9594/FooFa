package com.FooFa.FooFa_Server.repository;

import com.FooFa.FooFa_Server.domain.Memo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemoRepository extends MongoRepository<Memo, String> {
    List<Memo> findBySpotid(String id);
}
