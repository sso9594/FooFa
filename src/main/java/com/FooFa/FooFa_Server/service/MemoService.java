package com.FooFa.FooFa_Server.service;

import com.FooFa.FooFa_Server.domain.Memo;
import com.FooFa.FooFa_Server.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memorepository;

    public Memo save(Memo memo){ // 메모 저장
        return memorepository.save(memo);
    }

    public List<Memo> findAllMemo(){ // 모든 메모 리턴
        return memorepository.findAll();
    }

    public List<Memo> findAllById(String id){
        return memorepository.findAllById(Collections.singleton(id));
    }
}
