package org.example.calculator_backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.calculator_backend.model.response.list.ListPredictionResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {
    private ListPredictionResponse response;
    private String expression;
    private String result;
}
