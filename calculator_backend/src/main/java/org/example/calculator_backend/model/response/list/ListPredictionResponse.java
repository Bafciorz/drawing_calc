package org.example.calculator_backend.model.response.list;

import lombok.*;
import org.example.calculator_backend.model.response.PredictionResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPredictionResponse {
    private List<PredictionResponse> predictions;
}
