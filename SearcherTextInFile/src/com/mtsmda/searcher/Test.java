package com.mtsmda.searcher;

import java.util.Map;

import com.mtsmda.password_generator.Tester;

public class Test {

	public static void main(String[] args) {
		System.out.println(new StringBuilder("webar.niculescu").reverse());
		/*Tester.test(Test.class);
		 String s = "Tester.Test(Test.class);";
		 System.out.println(s.contains("Test"));*/
	}

	// private void firstTest() {
	// System.out.println("*********");
	// Map<String, Integer> search = new SearchEverywhereText()
	// .search("C:\\PROJECTS\\RRM-trunk\\rrm-core\\target\\classes\\RRM\\Config\\rules\\generate\\chartReview\\default\\diagnosisProcess\\RRM_ChartReview_AddDeleteDiagnosis.drl",
	// "package", SearchEverywhereText.SEARCH_ONLY_FILE,
	// SearchEverywhereText.EXACT_WORD);
	// System.out.println(search.size());
	// System.out.println("_________");
	// for (String key : search.keySet()) {
	// System.out.println("filename - " + key + ", count - "
	// + search.get(key));
	// }
	// System.out.println("*********\n");
	// }

	// private void firstTestPiece() {
	// System.out.println("*********");
	// Map<String, Integer> search = new SearchEverywhereText().search(
	// "C:\\Users\\dmitriim\\Desktop\\mvn clean test command.txt",
	// "test", SearchEverywhereText.SEARCH_ONLY_FILE,
	// SearchEverywhereText.EXACT_AND_CONTENT_WORD);
	// System.out.println(search.size());
	// System.out.println("_________");
	// for (String key : search.keySet()) {
	// System.out.println("filename - " + key + ", count - "
	// + search.get(key));
	// }
	// System.out.println("*********\n");
	// }

	//C:\\PROJECTS\\RRM-trunk\\rrm-core\\src\\main\\resources\\RRM\\Config\\rules
	//C:\\PROJECTS\\RRM-trunk\\rrm-core\\src\\main\\resources\\RRM\\Config\\rules\\external\\externalClaimUpdate\\default\\process
	//
	private void firstTestRecursive() {
		System.out.println("*********");
		Map<String, Integer> search = new SearchEverywhereText().search(
				"C:\\PROJECTS\\RRM-trunk\\rrm-core\\src\\", "TotalClaimPaidAmount",
				SearchEverywhereText.SEARCH_RECURSIVELY,
				SearchEverywhereText.EXACT_AND_CONTENT_WORD,
				SearchEverywhereText.CASE_INSENSITIVE);
		System.out.println(search.size());
		System.out.println("_________");
		for (String key : search.keySet()) {
			/*System.out.println("filename - " + key + ", count - "
					+ search.get(key));*/
			System.out.println(key);
		}
		System.out.println("*********\n");
	}

	// success - txt, drl, xml, html, java, css, xhtml, jsf, php, doc, docx
	
	// private void firstTestRecursiveEXACT_WORD() {
	// System.out.println("*********");
	// Map<String, Integer> search = new SearchEverywhereText().search(
	// "C:\\Users\\dmitriim\\Downloads\\example",
	// "test", SearchEverywhereText.SEARCH_RECURSIVELY,
	// SearchEverywhereText.EXACT_WORD);
	// System.out.println(search.size());
	// System.out.println("_________");
	// for (String key : search.keySet()) {
	// System.out.println("filename - " + key + ", count - "
	// + search.get(key));
	// }
	// System.out.println("*********\n");
	// System.out.println();
	// }

	// private void realTestRecursive() {
	// System.out.println("*********");
	// Map<String, Integer> search = new SearchEverywhereText().search(
	// "C:\\PROJECTS\\RRM-trunk\\", "commons-lang3",
	// SearchEverywhereText.SEARCH_RECURSIVELY,
	// SearchEverywhereText.EXACT_AND_CONTENT_WORD,
	// SearchEverywhereText.CASE_INSENSITIVE);
	// System.out.println(search.size());
	// System.out.println("_________");
	// for (String key : search.keySet()) {
	// System.out.println("filename - " + key + ", count - "
	// + search.get(key));
	// }
	// System.out.println("*********\n");
	// }
}