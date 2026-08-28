package org.example.calculator_backend.service;

import lombok.AllArgsConstructor;
import org.example.calculator_backend.model.response.PredictionResponse;
import org.example.calculator_backend.model.response.ResultResponse;
import org.example.calculator_backend.model.response.list.ListPredictionResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalculatorService {

    private final ModelInferenceService modelInferenceService;
    private final MathEvaluationService mathEvaluationService;


    public ResultResponse evaluateExpression(List<MultipartFile> files){
        ListPredictionResponse recognized = modelInferenceService.recognizeExpression(files);

        String expressionText = buildExpressionString(recognized);

        String evaluationResult = mathEvaluationService.evaluateExpression(expressionText);

        return new ResultResponse(recognized, expressionText, evaluationResult);
    }

    private String buildExpressionString(ListPredictionResponse recognized){
        return recognized.getPredictions().stream()
                .map(PredictionResponse::getRecognizedSymbol)
                .collect(Collectors.joining(""));
    }
}
