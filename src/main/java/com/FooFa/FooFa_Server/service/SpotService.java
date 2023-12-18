package com.FooFa.FooFa_Server.service;

import com.FooFa.FooFa_Server.domain.Spot;
import com.FooFa.FooFa_Server.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpotService {
    private final SpotRepository spotRepository;

    public Spot save(Spot spot){
        return spotRepository.save(spot);
    }

    public Spot findById(String id){
        return spotRepository.findById(id).get();
    }

}
