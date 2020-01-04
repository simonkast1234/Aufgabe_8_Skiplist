package main;

import Prog1Tools.IOTools;

public class Main {

	public static void main(String[] args) {
		System.out.println("------------------------------------------");

		char type = 's';
		do {
			type = Character.toLowerCase(IOTools.readChar("data type of skip list [s]string or [i]nt? "));
		} while(type != 's' && type != 'i');

		SkipList<String> listS = new SkipList<>();
		SkipList<Integer> listI = new SkipList<>();

		System.out.println("---------------------- SkipList was created -----------------------");
		char cmd;
		do {
			cmd = Character.toLowerCase(IOTools.readChar(
					"[a]dd [g]et [c]ontains [s]ize c[l]ear [p]rint sa[m]pleList [q]uit : "));
			switch (cmd) {
				// add
				case 'a':
					if(type == 's') {
						listS.add(IOTools.readString("Add: "));
					} else {
						listI.add(IOTools.readInt("Add: "));
					} break;
				// get
				case 'g':
					int index = IOTools.readInt("get value of index: ");
					if(type == 's') {
						System.out.println("Value of " + index + ". element is " + listS.get(index));
					} else {
						System.out.println("Value of " + index + ". element is " + listI.get(index));
					} break;
				// contains
				case 'c':
					if(type == 's') {
						String value = IOTools.readString("Check if list contains: ");
						System.out.println(listS.contains(value));
					} else {
						int value = IOTools.readInt("Check if list contains: ");
						System.out.println(listI.contains(value));
					}  break;
				// size
				case 's':
					if(type == 's') {
						System.out.println("The list contains " + listS.size() + " elements.");
					} else {
						System.out.println("The list contains " + listI.size() + " elements.");
					} break;
				// clear
				case 'l':
					if(type == 's') {
						listS.clear();
					} else {
						listI.clear();
					}
					System.out.println("List has been cleared.");
					break;
				// print
				case 'p':
					if(type == 's') {
						System.out.println(listS);
					} else {
						System.out.println(listI);
					} break;
				// sampleList
				case 'm':
					if(type == 's') {
						for (int i = 65; i <= 90; i++) {
							listS.add(String.valueOf((char) i));
						}
					} else {
						for (int i = -2; i < 21; i++) {
							listI.add(i);
						}
					} break;
				// quit
				case 'q':
					System.out.println("Good bye!");
					break;
				// invalid input
				default:
					System.out.println("Invalid input! Try again ...");
			}
		} while(cmd != 'q');


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

		/*
		SkipList<String> list = new SkipList<>();
		System.out.println("---------------------- SkipList was created -----------------------");
		char cmd;
		do {
			cmd = Character.toLowerCase(IOTools.readChar(
					"[a]dd [g]et [c]ontains [s]ize c[l]ear [p]rint sa[m]pleList [q]uit : "));
			switch (cmd) {
				// add
				case 'a':
					list.add(IOTools.readString("Add: "));
					break;
				// get
				case 'g':
					int index = IOTools.readInt("get value of index: ");
					System.out.println("Value of " + index + ". element is " + list.get(index));
					break;
				// contains
				case 'c':
					String value = IOTools.readString("Check if list contains: ");
					System.out.println(list.contains(value));
					break;
				// size
				case 's':
					System.out.println("The list contains " + list.size() + " elements.");
					break;
				// clear
				case 'l':
					list.clear();
					System.out.println("List has been cleared.");
					break;
				// print
				case 'p':
					System.out.println(list);
					break;
				// sampleList
				case 'm':
					for (int i = 65; i <= 90; i++) {
						list.add(String.valueOf((char)i));
					}
					break;
				// quit
				case 'q':
					System.out.println("Good bye!");
					break;
				// invalid input
				default:
					System.out.println("Invalid input! Try again ...");
			}
		} while(cmd != 'q');
		*/

	}
}