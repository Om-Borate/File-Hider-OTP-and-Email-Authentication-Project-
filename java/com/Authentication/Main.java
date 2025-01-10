package com.Authentication;

import views.Welcome;

public class Main {
	public static void main(String[] args) {
		try {
		Welcome w = new Welcome();
		do {
			try {
			w.WelcomeScreen();
			}catch(Exception e) {
				e.getMessage();
			}
			
		}while (true);
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		}
}