package com.FooFa.FooFa_Server.controller;

import com.FooFa.FooFa_Server.domain.Memo;
import com.FooFa.FooFa_Server.dto.MemoDto;
import com.FooFa.FooFa_Server.dto.MemoResponse;
import com.FooFa.FooFa_Server.service.MemoService;
import com.FooFa.FooFa_Server.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemoController {
    private final MemoService memoService;
    private final SpotService spotService;

    @PostMapping("/create")
    public ResponseEntity<Memo> saveMemo (MemoDto memoDto, @RequestParam MultipartFile[] multipartFiles) throws IOException {
        memoDto.setImgurl(memoService.storeFiles(Arrays.stream(multipartFiles).toList()));
        memoService.incCount(memoDto.getSpotid());

        return ResponseEntity.ok(
                memoService.save(memoDto.toEntity())
        );
    }

    @GetMapping("/showspot")
    public ResponseEntity<List<Memo>> viewSpotMemo (String spotid){
        return ResponseEntity.ok(
                memoService.findBySpotId(spotid)
        );
    }
    @GetMapping("/show")
    public ResponseEntity<Memo> viewMemo (String id) throws IOException {
        Memo target = memoService.findById(id);
        return ResponseEntity.ok(
                target
        );
    }

    @GetMapping("/show/image")
    public ResponseEntity<?> viewImage(String fileName) throws IOException {
        byte[] downloadImage = memoService.sendFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(memoService.contentType(fileName))
                .body(downloadImage);
    }

    @PutMapping("/{id}")
    public void updateMemo(@PathVariable String id, @RequestBody MemoDto memoDto){
        memoService.updateById(id, memoDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable String id){
        memoService.decCount(memoService.findById(id).getSpotid());
        memoService.deleteById(id);
    }
}
