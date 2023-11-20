package com.FooFa.FooFa_Server.service;

import com.FooFa.FooFa_Server.domain.Memo;
import com.FooFa.FooFa_Server.dto.MemoDto;
import com.FooFa.FooFa_Server.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memorepository;

    public Memo save(Memo memo){ // 메모 저장
        return memorepository.save(memo);
    }

    public List<MemoDto> findAllMemo(){ // 모든 메모 리턴
        List<Memo> memos = memorepository.findAll();
        List<MemoDto> result = new ArrayList<>();
        for (Memo memo : memos){
            MemoDto memoDto = responseDto(memo);
            result.add(memoDto);
        }
        return result;
    }

    public Memo findById(String id){ // id를 통한 메모 리턴
        return memorepository.findById(id).orElseThrow();
    }

    public Memo updateById (String id, MemoDto memoDto){
        Memo targetMemo = findById(id);
        return save(targetMemo.update(memoDto));
    }

    public void deleteById (String id){
        Optional<Memo> target = memorepository.findById(id);
        if(!target.isPresent()){
            throw new NullPointerException("유효하지 않은 게시글");
        }
        memorepository.deleteById(id);
    }

    private MemoDto responseDto (Memo memo){
        return new MemoDto(
                memo.getMemoid(),
                memo.getTextmemo(),
                memo.getImgurl(),
                memo.getLat(),
                memo.getLng()
        );
    }
}
