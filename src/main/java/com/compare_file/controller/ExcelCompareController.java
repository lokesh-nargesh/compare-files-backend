package com.compare_file.controller;

import com.compare_file.model.Difference;
import com.compare_file.service.ExcelCompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/compare")
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelCompareController {

    @Autowired
    private ExcelCompareService service;

    @PostMapping
    public List<Difference> compareFiles(@RequestParam("file1") MultipartFile file1,
                                         @RequestParam("file2") MultipartFile file2) {
        return service.compare(file1, file2);
    }
}
