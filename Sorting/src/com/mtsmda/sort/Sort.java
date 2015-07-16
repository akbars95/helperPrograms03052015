package com.mtsmda.sort;

public class Sort {
	private int ar[];
	private int methodSort;
	
	public static final int ASC = 1;
	public static final int DESC = -1;
	
	public Sort() {
		
	}
	
	public Sort(int [] mas) {
		this.ar = mas;
	}
	
	/**
	 * @param mas - array
	 * @param methodSort if 1 = ASC, if -1 = DESC 
	 * */
	public Sort(int mas[], int methodSort) {
		this.ar = mas;
		this.methodSort = methodSort;
	}
	
	private void swap(int []mas, int index){
		int temp = mas[index - 1];//	temp = 85
		mas[index - 1] = mas[index];//	index - 1 = 10
		mas[index] = temp;//	index = 85
	}
	// 8 9 10 56 95 25 36 456 85 10
	
	
	public int[] bubblesSort(){
		int mas[] = ar;//lenght = 10
		
		if(this.methodSort == ASC){
			for(int i = 1; i < mas.length; i++){//	i = 1, l = 10
				for(int j = mas.length - 1; j >= i; j--){//	j = 9; 
					if(mas[j - 1] > mas[j]){//	j-1 = 85	j = 10
						swap(mas, j);
					}
				}
			}
		}else if(this.methodSort == DESC){
			for(int i = 1; i < mas.length; i++){//	i = 1, l = 10
				for(int j = mas.length - 1; j >= i; j--){//	j = 9; 
					if(mas[j - 1] < mas[j]){//	j-1 = 85	j = 10
						swap(mas, j);
					}
				}
			}
		}
		return ar;
	}

	public int[] select(){
		int mas[] = ar;//	l = 10
		
		int min = 0, index = 0;
		//12 9 36 98 79 25 63 78 95 34
		for(int i = 0; i < mas.length; i++){
			min = mas[i];//	min = 12
			index = i;//	index = 0
				for(int j = i + 1; j < mas.length; j++){// j = 1
					if(mas[j] < min){//	mas[j] = 9	
						min = mas[j];//	min = 9
						index = j;//	index = 1
					}
				}
				
				if(mas[i] != mas[index]){
					mas[index] = mas[i]; // mas[index] = 12
					mas[i] = min; //	masp[i] = 9
				}
		}
		return mas;
	}
}
