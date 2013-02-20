package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.Map;

public class Questions {
    private Map<String, LinkedList> questions;

    public Questions(Map<String, LinkedList> questions) {
        this.questions = questions;
    }

    public Map<String, LinkedList> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, LinkedList> questions) {
        this.questions = questions;
    }

    void initQuestions() {
        LinkedList popQuestions = new LinkedList();
        LinkedList scienceQuestions = new LinkedList();
        LinkedList sportsQuestions = new LinkedList();
        LinkedList rockQuestions = new LinkedList();
        for (int i = 0; i < 50; i++) {
    		popQuestions.addLast("Pop Question " + i);
    		scienceQuestions.addLast(("Science Question " + i));
    		sportsQuestions.addLast(("Sports Question " + i));
    		rockQuestions.addLast("Rock Question " + i);
    	}
    	getQuestions().put("Pop", popQuestions);
    	getQuestions().put("Science", scienceQuestions);
    	getQuestions().put("Sports", sportsQuestions);
    	getQuestions().put("Rock", rockQuestions);
    }

    void askAbout(String currentCategory) {
        LinkedList q = getQuestions().get(currentCategory);
    	Object popNextQuestions = q.removeFirst();
        System.out.println(popNextQuestions);
    }
}