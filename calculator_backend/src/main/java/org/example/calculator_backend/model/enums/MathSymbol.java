package org.example.calculator_backend.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MathSymbol {
    ZERO(0, "0"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),

    ADD(10, "+"),
    DEC(11, "."),
    DIV(12, "/"),
    EQ(13, "="),
    MUL(14, "*"),
    SUB(15, "-"),
    X(16, "x"),
    Y(17, "y"),
    Z(18, "z");

    private final int modelIndex;
    private final String value;

    public static MathSymbol fromModelIndex(int index) {
        for (MathSymbol symbol : values()) {
            if (symbol.getModelIndex() == index) {
                return symbol;
            }
        }
        throw new IllegalArgumentException("Model returned undefined class index: " + index);
    }
}