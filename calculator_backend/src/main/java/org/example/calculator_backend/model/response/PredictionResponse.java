package org.example.calculator_backend.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResponse {
    private String recognizedSymbol;
}
