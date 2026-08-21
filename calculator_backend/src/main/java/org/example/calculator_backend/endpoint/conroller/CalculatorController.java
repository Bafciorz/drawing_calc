package org.example.calculator_backend.endpoint.conroller;

import lombok.RequiredArgsConstructor;
import org.example.calculator_backend.endpoint.CalculatorEndpoint;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.example.calculator_backend.service.ModelInferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CalculatorController implements CalculatorEndpoint {

    private final ModelInferenceService modelInferenceService;

    @Override
    public ResponseEntity<PredictionResponse> recognizeImage(MultipartFile file){
        PredictionResponse response  = modelInferenceService.recognizeImage(file);
        return ResponseEntity.ok(response);
    }
}
