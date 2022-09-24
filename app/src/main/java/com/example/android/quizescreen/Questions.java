package com.example.android.quizescreen;

public class Questions {
    public static String question[] ={
            "1. This is the flag of which Scandinavian country?",
            "2. What is the name of this world-known tourist destination in Italy?",
            "3. What is the name of this sport?",
            "4. In which city is this distinctive and famous building located?"
    };
    public static int questionImages[] ={
            R.drawable.question1,
            R.drawable.question2,
            R.drawable.question3,
            R.drawable.question4,

    };
    public static String choices[][] = {
            {"Sweden","Norway","Denmark"},
            {"Ponte Vecchio","Leaning Tower of Pisa","Milan Cathedral"},
            {"Cricket","Corkball","Kickball"},
            {"Sydney, Australia","Auckland, New Zealand","Bali, Indonesia"}
    };

    public static String correctAnswers[] = {
            "Denmark",
            "Leaning Tower of Pisa",
            "Cricket",
            "Sydney, Australia"
    };
}
