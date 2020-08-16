package baek;

import java.util.Scanner;

public class croatia {
	public static void main(String[] args) {

		String[] cro = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		
		Scanner sc = new Scanner(System.in);
		
		String in = sc.nextLine();
		
		sc.close();

		for (int i=0; i<8; i++)
			in = in.replace(cro[i], "*");

		System.out.println(in.length());

	}
}
