package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.Map;

public class Questions {
    private Map<String, LinkedList<String>> questions;

    public Questions(Map<String, LinkedList<String>> questions) {
        this.questions = questions;
    }

    void initQuestions() {
        LinkedList<String> popQuestions = new LinkedList<String>();
        LinkedList<String> scienceQuestions = new LinkedList<String>();
        LinkedList<String> sportsQuestions = new LinkedList<String>();
        LinkedList<String> rockQuestions = new LinkedList<String>();
        for (int i = 0; i < 50; i++) {
    		popQuestions.addLast("Pop Question " + i);
    		scienceQuestions.addLast(("Science Question " + i));
    		sportsQuestions.addLast(("Sports Question " + i));
    		rockQuestions.addLast("Rock Question " + i);
    	}
    	questions.put("Pop", popQuestions);
    	questions.put("Science", scienceQuestions);
    	questions.put("Sports", sportsQuestions);
    	questions.put("Rock", rockQuestions);
    }

    void askAbout(String currentCategory) {
        LinkedList<String> q = questions.get(currentCategory);
    	String popNextQuestions = q.removeFirst();
        System.out.println(popNextQuestions);
    }
}