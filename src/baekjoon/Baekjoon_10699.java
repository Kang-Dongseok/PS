package baekjoon;

import java.text.SimpleDateFormat;
import java.util.Date;

// 오늘 날짜
public class Baekjoon_10699 {

    public static void main(String[] args){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String now = sdf.format(new Date());
    	System.out.println(now);
    }
}