package model;

import java.util.ArrayList;

public class Question {

	private String statement;
	private ArrayList<String> options;
	private int answer;
	
	public Question(String statement, ArrayList<String> options, int answer) {
		this.statement = statement;
		this.options = options;
		this.answer = answer;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
}
