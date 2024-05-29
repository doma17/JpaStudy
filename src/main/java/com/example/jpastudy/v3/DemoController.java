package com.example.jpastudy.v3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v3/demo")
@RestController
public class DemoController {

    List<String> list = new ArrayList<>();
    int listLimit = 10;

    @GetMapping("/list")
    public ResponseDto getLists() {
        return ResponseUtil.success("리스트 조회 성공", list);
    }

    @GetMapping("/list/add")
    public ResponseDto addListElement(@RequestParam String element) {
        if (list.size() < listLimit) {
            list.add(element);
            return ResponseUtil.success("리스트 요소 추가 성공", list);
        }
        return ResponseUtil.error("리스트가 꽉참", list);
    }

}
