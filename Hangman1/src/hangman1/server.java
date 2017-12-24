package hangman1;

import java.io.*;
import java.util.*;
import java.net.*;

public class server {
	private static final int PORT = 2000;
	private static ServerSocket svrSocket;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			svrSocket = new ServerSocket(PORT);
		} catch (IOException ioEp) {
			System.out.println("\nUnable to set up port!");
			System.exit(1);

		}
		do {
			Socket link = null;
			try {
				link = svrSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("trouble");
			}
			System.out.println("\nNew client accessed!");
			UserHdlr handler = new UserHdlr(link);
			handler.start();
		} while (true);
	}

}

class UserHdlr extends Thread {
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	private String gword;

	public UserHdlr(Socket socket) {
		client = socket;
		try {
			input = new Scanner(client.getInputStream());
			// System.out.println(input);
			output = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException ioEp) {
			ioEp.printStackTrace();
		}
	}

	public void run() {
		int score = 0, n, judge, t;
		// output.println("type any key to continue");
		// output.println("game start! Please enter your guessing character.");
		String message = input.nextLine(), str = "";

		do {
			// System.out.println(input);
			gword = getword1.randomWord();
			str = "";
			n = gword.length();// length of word which is being guessed
			/* create start name */
			for (int i = n; i > 0; i--) {
				str = str + "_";
			}
			StringBuilder s = new StringBuilder(str);
			StringBuilder s1 = new StringBuilder(str);
			for (judge = 0, t = 1; t < n; t++) {
				if (message.length() == 1) { // determine whether input is char or string
					char messAge = message.charAt(0);// convert input character into char
					System.out.println(message);
					for (int i = 0; i < n; i++) { // determine whether input is correct
						char character = gword.charAt(i);
						if (messAge == character) {
							s1 = s1.replace(i, i + 1, message);
						}
					}
					if (s1.toString().equals(s.toString())) {
						// output.println(s1);
						output.printf("%10s %20s %10s\n", "word", "remaing attempt", "score");
						output.printf("%10s %20s %10s\n", s1.toString(), n - t, score);
						output.printf("you fail this time, enter again!\n");
					} else {
						// output.println(s1);
						output.printf("%10s %20s %10s\n", "word", "remaing attempt", "score");
						t--;
						output.printf("%10s %20s %10s\n", s1.toString(), n - t, score);
						output.printf("right,continue!\n");
						// judge
						s = new StringBuilder(s1.toString());
						if (gword.equals(s.toString())) {
							score = score + 2;
							output.printf("success!your score is " + (score - 1) + "\n");
							output.printf("try again(Enter'Exit'to exit)\n");
							output.printf("enter new guessing character to continue\n");

							judge = 1;
							break;
						}
						// output.printf("-\n");
					}

				} else {
					// the situation of string input
					if (gword.equals(message)) {
						score = score + 2;
						output.printf("success!your score is " + (score - 1) + "\n");
						output.printf("try again(Enter'Exit'to exit)\n");
						output.printf("enter new guessing character to continue\n");

						judge = 1;
						break;
					} else {
						output.printf("%10s %20s %10s\n", "word", "remaing attempt", "score");
						output.printf("%10s %20s %10s\n", s1.toString(), n - t, score);
						output.printf("you fail this time, enter again!\n");
					}

				}
				/*
				 * if(gword.equals(s.toString())) { output.printf("success!\n");
				 * output.printf("try again(Enter'Exit'to exit)\n");
				 * output.printf("-enter new guessing character to continue\n"); score=score+2;
				 * judge=1; break; }
				 */
				try {
					message = input.nextLine();
				} catch (Exception o) {
					interrupt();
				}
			} //
			score--;
			if (judge == 0) {
				output.printf("You fail this time,the word is " + gword + "\n");
				output.printf("please try again(Enter'Exit'to exit)\n");
				output.printf("enter new guessing character to continue\n");
			}
			try {
				message = input.nextLine();
			} catch (Exception o) {
				interrupt();
			}
		} while (!message.equals("Exit"));

	}
}