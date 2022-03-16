package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 상근날드
public class Baekjoon_5543 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int burger1 = Integer.parseInt(br.readLine());
		int burger2 = Integer.parseInt(br.readLine());
		int burger3 = Integer.parseInt(br.readLine());
		int coke = Integer.parseInt(br.readLine());
		int sprite = Integer.parseInt(br.readLine());
		int minBurgerPrice = Math.min(Math.min(burger1, burger2), burger3);
		int minBeveragePrice = Math.min(coke, sprite);
		System.out.println(minBurgerPrice+minBeveragePrice-50);
	}
}
