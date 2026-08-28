package org.example.calculator_backend.service;

import org.example.calculator_backend.model.response.ResultResponse;
import org.matheclipse.core.eval.ExprEvaluator;
import org.springframework.stereotype.Service;

@Service
public class MathEvaluationService {

    public String evaluateExpression(String expression){
        try{
            String symjaInput = expression;

            if (symjaInput.contains("=")) {
                symjaInput = symjaInput.replace("=", "==");
            }
            ExprEvaluator evaluator = new ExprEvaluator();
            String rawResult = evaluator.eval(symjaInput).toString();
            return rawResult.replace("==", "=");
        } catch(Exception e){
            return "Cannot evaluate expression";
        }
    }
}
