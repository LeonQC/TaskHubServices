//package com.junlinc.rest.controller;
//
//
//import com.junlinc.rest.domain.Title;
//import com.junlinc.rest.service.TitleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("API/v1/tasks")
//public class TitleController {
//    private final TitleService titleService;
//    @Autowired
//    public TitleController(TitleService titleService) {
//        this.titleService = titleService;
//    }
//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public ResponseEntity<Title> getTitleById(@PathVariable String id) {
//        Title title = titleService.getTitleById(id);
//        return ResponseEntity.ok(title);
//
//    }
//
//    @PutMapping(value = "/{id}/title")
//    @ResponseBody
//    public ResponseEntity<Title> updateTitle(@PathVariable String id,@RequestBody Title newTitle) {
//        Title updatedTitle = titleService.updateTitle(id, newTitle.getTitle());
//        return ResponseEntity.ok(updatedTitle);
//    }
//}
