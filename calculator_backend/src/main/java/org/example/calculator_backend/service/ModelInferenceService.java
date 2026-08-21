package org.example.calculator_backend.service;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import jakarta.annotation.PostConstruct;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class ModelInferenceService {

    private OrtEnvironment env;
    private OrtSession session;

    @PostConstruct
    public void init() throws Exception{
        this.env = OrtEnvironment.getEnvironment();

        ClassPathResource resource = new ClassPathResource("model_inference_service.conf");
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] modelBytes = inputStream.readAllBytes();
            this.session = env.createSession(modelBytes, new OrtSession.SessionOptions());
        }
        System.out.println("Model inference service initialized");
    }

    public PredictionResponse recognizeImage(MultipartFile file){
        return new PredictionResponse("a");
    }
}
