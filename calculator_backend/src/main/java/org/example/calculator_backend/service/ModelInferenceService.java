package org.example.calculator_backend.service;

import lombok.AllArgsConstructor;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ModelInferenceService {
    public PredictionResponse recognizeImage(MultipartFile file){
        return new PredictionResponse("a");
    }
}
