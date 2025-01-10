package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;
import model.User;
import sevice.GenerateOTP;
import sevice.SendOTPServices;

public class Welcome {
	public void WelcomeScreen() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the app");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to sign up");
		System.out.println("Press 0 to exit");
		int choice =0;
		try {
			choice=Integer.parseInt(br.readLine());
		}catch(IOException e) {
			e.printStackTrace();
		}
			switch(choice) {
			case 1 -> login();
			case 2 -> signup();
			case 0 -> System.exit(0);
			}
	}

	private void login() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the email");
		String email = scanner.nextLine();
		
		if(UserDAO.isExists(email)) {
			String otp = GenerateOTP.getOTP();
			SendOTPServices.sendEmail(email,otp);
			String userOTP = scanner.nextLine();
			if(userOTP.equals(otp)) {
					new UserView(email).home();
			}
			else {
				System.out.println("Wrong OTP ");
			}
			}
		else {
			
			System.out.println("User Not Found");
		}
		
	}

	private void signup() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name :");
		String name = scanner.nextLine();
		System.out.print("Enter your Email : ");
		String email = scanner.nextLine();
		//if()
		String otp = GenerateOTP.getOTP();
		SendOTPServices.sendEmail(email,otp);
		String userOTP = scanner.nextLine();
		if(userOTP.equals(otp)) {
			System.out.println("WELCOME ");
			User user = new User(name,email);
			try {
				int response = UserDAO.saveUser(user);
				switch(response) {
				case 0 -> System.out.println("User Register Successfully");
				case 1 ->System.out.println("User Already Exists");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Wroung OTP ");
		}
		
	}
}
