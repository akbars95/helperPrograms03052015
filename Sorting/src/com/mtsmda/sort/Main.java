package com.mtsmda.sort;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int mas[] = new int[10];
		
		//input date
		System.out.println("Unsorted array:");
		for(int i = 0; i < mas.length; i++){
			mas[i] = (int) Math.round(Math.random() * 100);
			System.out.print(mas[i] + " ");
		}
		
		Sort bubbleSort = new Sort(mas, Sort.ASC);
		int bubble[] = bubbleSort.bubblesSort();
		System.out.println("\nBubble Sort:");
		
		//output date
		for(int i = 0; i < bubble.length; i++){
			System.out.print(bubble[i] + " ");
		}
		
		Sort selectSort = new Sort(mas);
		int select[] = selectSort.select();
		System.out.println("\nSelect Sort:");
		
		for(int i = 0; i < select.length; i++){
			System.out.print(select[i] + " ");
		}
		
	}

}
