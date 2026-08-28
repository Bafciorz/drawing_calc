package org.example.calculator_backend.endpoint.controller;

import lombok.RequiredArgsConstructor;
import org.example.calculator_backend.endpoint.CalculatorEndpoint;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.example.calculator_backend.model.response.list.ListPredictionResponse;
import org.example.calculator_backend.service.ModelInferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CalculatorController implements CalculatorEndpoint {

    private final ModelInferenceService modelInferenceService;

    @Override
    public ResponseEntity<PredictionResponse> recognizeImage(MultipartFile file){
        PredictionResponse response  = modelInferenceService.recognizeImage(file);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ListPredictionResponse> recognizeExpression(List<MultipartFile> files){
        ListPredictionResponse response = modelInferenceService.recognizeExpression(files);
        return ResponseEntity.ok(response);
    }
}
