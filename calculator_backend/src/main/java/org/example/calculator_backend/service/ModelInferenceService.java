package org.example.calculator_backend.service;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import jakarta.annotation.PostConstruct;
import org.example.calculator_backend.model.enums.MathSymbol;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.Collections;

@Service
public class ModelInferenceService {

    private OrtEnvironment env;
    private OrtSession session;

    @PostConstruct
    public void init() throws Exception{
        this.env = OrtEnvironment.getEnvironment();

        ClassPathResource resource = new ClassPathResource("math_calculator_model.onnx");
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] modelBytes = inputStream.readAllBytes();
            this.session = env.createSession(modelBytes, new OrtSession.SessionOptions());
        }
        System.out.println("Model inference service initialized");
    }

    public PredictionResponse recognizeImage(MultipartFile file){
        try{
            BufferedImage image = readImage(file);

            float[] inputTensorData = preprocessImage(image);

            int bestClassIndex = runInference(inputTensorData);

            MathSymbol recognizedSymbol = MathSymbol.fromModelIndex(bestClassIndex);
            return new PredictionResponse(recognizedSymbol.getValue());
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private BufferedImage readImage(MultipartFile file) throws Exception{
        BufferedImage image = ImageIO.read(file.getInputStream());
        if(image == null){
            throw new IllegalArgumentException("image is null");
        }
        return image;
    }

    private float[] preprocessImage(BufferedImage originalImage){
        BufferedImage resizedImage = new BufferedImage(28,28,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage.getScaledInstance(28,28,Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();

        float[] inputData = new float[28 * 28];
        int index = 0;
        for (int y = 0; y < 28; y++) {
            for (int x = 0; x < 28; x++) {
                int rgb = resizedImage.getRGB(x, y) & 0xFF;
                inputData[index++] = (rgb / 255.0f - 0.5f) / 0.5f;
            }
        }
        return inputData;
    }


    private int runInference(float[] inputData) throws OrtException {
        long[] shape = new long[]{1, 1, 28, 28};
        try (OnnxTensor inputTensor = OnnxTensor.createTensor(env, FloatBuffer.wrap(inputData), shape);
            OrtSession.Result results = session.run(Collections.singletonMap("input",inputTensor))){

            float[][] outputArray = (float[][]) results.get(0).getValue();
            float[] probabilities = outputArray[0];

            return findArgMax(probabilities);
        }
    }

    private int findArgMax(float[] probabilities) {
        int bestIndex = 0;
        float maxScore = probabilities[0];
        for (int i = 1; i < probabilities.length; i++) {
            if (probabilities[i] > maxScore) {
                maxScore = probabilities[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}
