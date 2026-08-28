package org.example.calculator_backend.service;

import org.matheclipse.core.eval.ExprEvaluator;
import org.springframework.stereotype.Service;

@Service
public class MathEvaluationService {

    public String evaluateExpression(String expression){
        try{
            ExprEvaluator evaluator = new ExprEvaluator();
            return evaluator.eval(expression).toString();
        } catch(Exception e){
            return "Cannot evaluate expression";
        }
    }
}
