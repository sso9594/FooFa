package com.FooFa.FooFa_Server.service;

import com.FooFa.FooFa_Server.domain.Memo;
import com.FooFa.FooFa_Server.domain.Spot;
import com.FooFa.FooFa_Server.dto.MemoDto;
import com.FooFa.FooFa_Server.repository.MemoRepository;
import com.FooFa.FooFa_Server.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memorepository;
    private final SpotRepository spotRepository;
    private final MongoTemplate mongoTemplate;

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
    public List<Memo> findBySpotId(String spotid){
        return memorepository.findBySpotid(spotid);
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
                memo.getLng(),
                memo.getSpotid()
        );
    }

    public String getFullPath(String filename){
//        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + File.separator + "main" + File.separator + "resources" + File.separator + "static" +  File.separator + "images" +File.separator;
        String filePath = "/Users/sinseung-yong/Desktop/FooFa/images/";
        return filePath + filename;
    }

    public List<String> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<String> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException{
        if(multipartFile.isEmpty()){
            return null;
        }

        String originFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);

        String fullPath = getFullPath(storeFileName);
        File directory = new File(fullPath).getParentFile();
        if(!directory.exists()){
            directory.mkdirs();
        }

        multipartFile.transferTo(new File(fullPath));

        return storeFileName;
    }

    private String createStoreFileName(String originFileName){
        String uuid = UUID.randomUUID().toString();
        String extention = extractExt(originFileName);
        return uuid + "." + extention;
    }

    private String extractExt(String originFileName){
        int pos = originFileName.lastIndexOf(".");
        return originFileName.substring(pos + 1);
    }

    public byte[] sendFile (String fileName) throws IOException {
        Path filePath = Path.of(getFullPath(fileName));
        byte[] imageBytes = Files.readAllBytes(filePath);
        return imageBytes;
    }

    public MediaType contentType(String keyname){
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length-1];
        switch (type){
            case"txt":
                return MediaType.TEXT_PLAIN;
            case"png":
                return MediaType.IMAGE_PNG;
            case"jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public void incCount(String id){
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update().inc("countMemo",1);
        mongoTemplate.updateFirst(query, update, Spot.class);
    }

    public void decCount(String id){
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update().inc("countMemo",-1);
        mongoTemplate.updateFirst(query, update, Spot.class);
    }
}
