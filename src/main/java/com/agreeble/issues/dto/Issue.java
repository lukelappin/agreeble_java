package com.agreeble.issues.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Luke Lappin
 * Date: 4/26/14
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Issue {

    private int issue;
    private List<Integer> thoughts;

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public List<Integer> getThoughts() {
        return thoughts;
    }

    public void setThoughts(List<Integer> thoughts) {
        this.thoughts = thoughts;
    }


}
