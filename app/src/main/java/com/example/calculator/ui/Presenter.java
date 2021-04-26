package com.example.calculator.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.calculator.core.Calculator;
import com.example.calculator.core.Operation;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Presenter implements Parcelable {
    private CalculatorView view;
    private Calculator calculator;
    private ArrayList<String> expression = new ArrayList<>();

    public Presenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public String getExpressionString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < expression.size(); i++) {
            if (res.length() > 0) {
                res.append(expression.get(i));
            } else {
                res = new StringBuilder(String.valueOf(expression.get(0)));
            }
        }
        return res.toString();
    }

    protected Presenter(Parcel in) {
        expression = in.createStringArrayList();
    }

    public static final Creator<Presenter> CREATOR = new Creator<Presenter>() {
        @Override
        public Presenter createFromParcel(Parcel in) {
            return new Presenter(in);
        }

        @Override
        public Presenter[] newArray(int size) {
            return new Presenter[size];
        }
    };

    public void onButton1Clicked() {
        expressionFillingNum("1");
    }

    public void onButton2Clicked() {
        expressionFillingNum("2");
    }

    public void onButton3Clicked() {
        expressionFillingNum("3");
    }

    public void onButton4Clicked() {
        expressionFillingNum("4");
    }

    public void onButton5Clicked() {
        expressionFillingNum("5");
    }

    public void onButton6Clicked() {
        expressionFillingNum("6");
    }

    public void onButton7Clicked() {
        expressionFillingNum("7");
    }

    public void onButton8Clicked() {
        expressionFillingNum("8");
    }

    public void onButton9Clicked() {
        expressionFillingNum("9");
    }

    public void onButton0Clicked() {
        expressionFillingNum("0");
    }

    public void onButtonPlusClicked() {
        expressionFillingSign("+");
    }

    public void onButtonMinusClicked() {
        expressionFillingSign("-");
    }

    public void onButtonMultiClicked() {
        expressionFillingSign("*");
    }

    public void onButtonDivClicked() {
        expressionFillingSign("/");
    }

    public void onButtonPointClicked() {
        if (!expression.get(expression.size() - 1).equals(".") && !isSign(expression.get(expression.size() - 1))) {
            expression.add(expression.get(expression.size() - 1).concat("."));
            expression.remove(expression.size() - 2);
        }
        printResult(expression);
    }

    public void onButtonCancelClicked() {
        if (!expression.isEmpty()) {
            expression.remove(expression.size() - 1);
        }
        printResult(expression);
    }

    public void onButtonEqualClicked() {
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        String result = decimalFormat.format(parseExpression());
        view.showResult(result);
    }

    private void expressionFillingNum(String num) {
        if (expression.isEmpty()) {
            expression.add(num);
        } else if (expression.get(expression.size() - 1).endsWith(".") || isNum(expression.get(expression.size() - 1))) {
            expression.add(expression.get(expression.size() - 1).concat(num));
            expression.remove(expression.size() - 2);
        } else {
            expression.add(num);
        }
        printResult(expression);
    }

    private void expressionFillingSign(String sign) {
        if (isSign(expression.get(expression.size() - 1)) || expression.get(expression.size() - 1).equals(".")) {
            expression.remove(expression.size() - 1);
        }
        expression.add(sign);
        printResult(expression);
    }

    private boolean isNum(String str) {
        return str.endsWith("1") || str.endsWith("2") || str.endsWith("3") ||
                str.endsWith("4") || str.endsWith("5") || str.endsWith("6") ||
                str.endsWith("7") || str.endsWith("8") || str.endsWith("9") || str.endsWith("0");
    }

    private boolean isSign(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    public double parseExpression() {
        double result;
        while (expression.size() != 1) {
            if (expression.contains("/")) {
                int index = expression.indexOf("/");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = calculator.binaryOperation(first, second, Operation.DIV);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            } else if (expression.contains("*")) {
                int index = expression.indexOf("*");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = calculator.binaryOperation(first, second, Operation.MULT);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            } else if (expression.contains("-")) {
                int index = expression.indexOf("-");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = calculator.binaryOperation(first, second, Operation.SUB);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            } else if (expression.contains("+")) {
                int index = expression.indexOf("+");
                double first = Double.parseDouble(expression.get(index - 1));
                double second = Double.parseDouble(expression.get(index + 1));
                result = calculator.binaryOperation(first, second, Operation.ADD);
                expression.add(index - 1, String.valueOf(result));
                expression.remove(index + 2);
                expression.remove(index + 1);
                expression.remove(index);
            }
        }
        return Double.parseDouble(expression.get(0));
    }

    public void printResult(ArrayList<String> list) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (res.length() > 0) {
                res.append(list.get(i));
            } else {
                res = new StringBuilder(String.valueOf(list.get(0)));
            }
        }
        view.showResult(res.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(expression);
    }
}
