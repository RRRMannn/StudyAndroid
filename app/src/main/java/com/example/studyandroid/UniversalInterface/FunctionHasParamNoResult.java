package com.example.studyandroid.UniversalInterface;

public abstract class FunctionHasParamNoResult<P> extends Function {
    public FunctionHasParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void funciotn(P p);
}
