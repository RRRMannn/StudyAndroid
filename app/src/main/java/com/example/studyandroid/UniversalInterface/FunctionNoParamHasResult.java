package com.example.studyandroid.UniversalInterface;

public abstract class FunctionNoParamHasResult<T> extends Function {
    public FunctionNoParamHasResult(String functionName) {
        super(functionName);
    }

    public abstract T function();
}
