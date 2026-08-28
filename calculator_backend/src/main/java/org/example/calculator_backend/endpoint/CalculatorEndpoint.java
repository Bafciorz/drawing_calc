package org.example.calculator_backend.endpoint;


import org.example.calculator_backend.model.response.PredictionResponse;
import org.example.calculator_backend.model.response.list.ListPredictionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/calculator")
public interface CalculatorEndpoint {

    @PostMapping("/recognize")
    ResponseEntity<PredictionResponse> recognizeImage(@RequestParam("image") MultipartFile file);

    @PostMapping("/recognize-expression")
    ResponseEntity<ListPredictionResponse> recognizeExpression(@RequestParam("files") List<MultipartFile> files);
}
