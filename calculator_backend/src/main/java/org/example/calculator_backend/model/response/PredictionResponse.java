package org.example.calculator_backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PredictionResponse {
    private String recognizedSymbol;
}
