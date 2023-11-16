package com.FooFa.FooFa_Server.repository;

import com.FooFa.FooFa_Server.domain.Memo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemoRepository extends MongoRepository<Memo, String> {
}
