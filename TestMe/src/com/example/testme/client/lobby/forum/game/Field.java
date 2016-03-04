package com.example.testme.client.lobby.forum.game;

import com.vaadin.ui.Button;

/**
 * @author Alexander Thomas
 * @date 28.02.2016
 */
@SuppressWarnings("serial")
public class Field extends Button {

	private boolean active = false;

	private int number = -1;

	public Field(String name, int number) {
		super(name);

		this.number = number;
	}
	
	public Field(int number) {
		super();

		this.number = number;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean isActive(){
		return this.active;
	}
}
