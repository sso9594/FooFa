package com.FooFa.FooFa_Server.controller;

import com.FooFa.FooFa_Server.domain.Spot;
import com.FooFa.FooFa_Server.dto.SpotDto;
import com.FooFa.FooFa_Server.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/spot")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpotController {

    private final SpotService spotService;
    @PostMapping("/create")
    public ResponseEntity<Spot> saveSpot (SpotDto spotDto) {
        return ResponseEntity.ok(
                spotService.save(spotDto.toEntity())
        );
    }

}
