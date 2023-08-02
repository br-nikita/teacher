package com.example.teacher;

public class Line {

    private String variant; // название
    private String number;  // столица
    private String answer;
    private String right_answer;

    public Line(String variant, String number, String answer, String right_answer){

        this.variant=variant;
        this.number=number;
        this.answer=answer;
        this.right_answer=right_answer;
    }
    public String getVariant() {
        return this.variant;
    }
    public String getNumber() {
        return this.number;
    }
    public String getAnswer() {
        return this.answer;
    }
    public String getRight_answer() {
        return this.right_answer;
    }
}
