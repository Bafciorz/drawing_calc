package org.example.calculator_backend.service;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ModelInferenceService {

    private OrtEnvironment env;
    private OrtSession session;



    public PredictionResponse recognizeImage(MultipartFile file){
        return new PredictionResponse("a");
    }
}
