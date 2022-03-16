package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 나이순 정렬
public class Baekjoon_10814 {

	static class Person implements Comparable<Person>{
		int age;
		String name;
		int no;
		public Person(int age, String name, int no) {
			this.age = age;
			this.name = name;
			this.no = no;
		}
		@Override
		public int compareTo(Person o) {
			if(this.age==o.age) {
				return Integer.compare(this.no, o.no);
			}else return Integer.compare(this.age, o.age);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Person[] arr = new Person[N];
		String[] str;
		for(int i=0; i<N; ++i) {
			str = br.readLine().split(" ");
			int age = Integer.parseInt(str[0]);
			String name = str[1];
			arr[i] = new Person(age, name, i);
		}
		Arrays.sort(arr);
		for(int i=0; i<N; ++i) {
			System.out.println(arr[i].age+" "+arr[i].name);
		}
	}
}