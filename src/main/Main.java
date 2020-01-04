package main;

import Prog1Tools.IOTools;

public class Main {


	public static void main(String[] args) {
		SkipList<Integer> list = new SkipList<>();
		System.out.println(list);

		int value = 0;
		while(true) {
			value = IOTools.readInt("int number to add: ");
			if(value == 999) break;
			list.add(value);
			System.out.println(list);
		}
		System.out.println("bye");

		/*
		list.add(1);
		list.add(3);
		list.add(6);
		list.add(7);
		list.add(12);
		list.add(14);
		list.add(21);
		list.add(34);
		list.add(36);
		list.add(37);


		System.out.printf("test get(5) should be:14 is:%d\n",list.get(5));
		
		System.out.printf("test contains(7) should be:true is:%b\n",list.contains(7));
		System.out.printf("test contains(37) should be:true is:%b\n",list.contains(37));
		System.out.printf("test contains(-1) should be:false is:%b\n",list.contains(-1));
		
		System.out.printf("test toString() should be:\n"
				+ "head -> 1 -> 7 -> 21 -> 36 -> null\r\n" + 
				"head -> 1 -> 3 -> 6 -> 7 -> 12 -> 14 -> 21 -> 34 -> 36 -> 37 -> null\r\n" + 
				"\nis:\n%s\n",list);


		 */



	}




}
