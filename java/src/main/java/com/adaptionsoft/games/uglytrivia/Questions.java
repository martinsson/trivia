package com.adaptionsoft.games.uglytrivia;

import java.util.List;

/**
 * Created by johan on 13/02/17.
 */
public class Questions {
    private final int nbOfQuestions;
    private int nbQuestionsAsked;
    private List<String> questions;

    public Questions(List<String> questions) {

        this.questions = questions;
        this.nbOfQuestions = questions.size();
        this.nbQuestionsAsked = 0;
    }

    public String nextQuestion() {
        int currentQuestionIndex = this.nbQuestionsAsked % this.nbOfQuestions;
        this.nbQuestionsAsked++;
        return this.questions.get(currentQuestionIndex);
    }


}
