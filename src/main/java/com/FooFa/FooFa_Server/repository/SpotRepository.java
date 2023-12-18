package com.FooFa.FooFa_Server.repository;

import com.FooFa.FooFa_Server.domain.Spot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpotRepository extends MongoRepository<Spot, String> {

}
