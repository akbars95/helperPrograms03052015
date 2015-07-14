package com.mtsmda.searcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEverywhereText {

	public static final String ERROR = "ERROR";

	public static final String AVAILABLE_FILE_EXTENTIONS = "availableFileExtentions.txt";

	public static final StringBuilder STANDART_FILE_EXTENTIONS = new StringBuilder(
			"html, css, php, txt, doc, docx, drl, java");

	public static final Integer SEARCH_ONLY_FILE = 1;
	public static final Integer SEARCH_RECURSIVELY = 2;

	public static final Integer EXACT_WORD = 0;
	public static final Integer EXACT_AND_CONTENT_WORD = 1;

	public static final Boolean CASE_SENSETIVE = true;
	public static final Boolean CASE_INSENSITIVE = false;

	private List<File> files = new ArrayList<File>();

	private static List<String> fileExtentions = new ArrayList<String>();

	static {
		getAvailableFileExtentions(null);
	}

	public static StringBuilder getAvailableFileExtentions(
			List<String> extentions) {
		BufferedReader bufferedReader = null;
		StringBuilder fileSource = new StringBuilder();
		try {
			File file = new File(AVAILABLE_FILE_EXTENTIONS);
			bufferedReader = new BufferedReader(new FileReader(file));
			String currentLine = null;
			while ((currentLine = bufferedReader.readLine()) != null) {
				fileSource.append(currentLine);
			}
			parseFileWithExtensions(fileSource, extentions);

		} catch (Exception e) {
			parseFileWithExtensions(STANDART_FILE_EXTENTIONS, null);
		} finally {
			closeBR(bufferedReader);
		}
		return fileSource;
	}

	private static void closeBR(BufferedReader bufferedReader) {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void parseFileWithExtensions(StringBuilder fileSource,
			List<String> extentions) {
		String[] split = fileSource.toString().split("[,]");
		for (String current : split) {
			if (current != null && !current.isEmpty()) {
				current = current.trim();
				if (extentions == null) {
					fileExtentions.add(current);
				} else {
					extentions.add(current);
				}

			}
		}
	}

	private Map<File, StringBuilder> filesSources = new HashMap<File, StringBuilder>();

	public Map<String, Integer> search(String filePath, String searchWords,
			Integer searchMethod, Integer searchMethodWord,
			Boolean isCaseSensetive) {
		Map<String, Integer> mapResultFilePathAndCount = new HashMap<String, Integer>();
		try {
			if (filePath != null && !filePath.isEmpty() && !filePath.equals("")
					&& searchWords != null && !searchWords.isEmpty()) {
				File file = new File(filePath);
				if (file.exists() && file.canRead()) {
					if (searchMethod.equals(SEARCH_ONLY_FILE)) {
						if (file.isFile()) {
							if (fileFilter(file)) {
								StringBuilder fileText = getFileText(file);
								searchWords(fileText, searchWords,
										mapResultFilePathAndCount,
										file.getAbsolutePath(),
										searchMethodWord, isCaseSensetive);
							}
						} else {
							throw new Exception(
									"Chosen method search only file, but this is not a file");
						}
					} else if (searchMethod.equals(SEARCH_RECURSIVELY)) {
						if (file.isDirectory()) {
							recursiveSearchFiles(file);
							if (!files.isEmpty()) {
								for (File curFile : files) {
									filesSources.put(curFile,
											getFileText(curFile));
								}

								for (File fileCur2 : filesSources.keySet()) {
									searchWords(filesSources.get(fileCur2),
											searchWords,
											mapResultFilePathAndCount,
											fileCur2.getAbsolutePath(),
											searchMethodWord, isCaseSensetive);
								}
							}
						} else {
							throw new Exception(
									"Chosen method search recursively, but this is not directory");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapResultFilePathAndCount.put(ERROR, -1);
			mapResultFilePathAndCount.put(e.getMessage(), -1);
		}
		return mapResultFilePathAndCount;
	}

	private boolean fileFilter(File file) {
		if (file.exists()) {
			int lastIndexOf = file.getAbsolutePath().lastIndexOf(".");
			String extension = file.getAbsolutePath()
					.substring(lastIndexOf + 1);
			return fileExtentions.contains(extension);
		}
		return false;
	}

	private void recursiveSearchFiles(File file) {
		File[] listFiles = file.listFiles();
		for (File currentFile : listFiles) {
			if (currentFile.isFile()) {
				if (fileFilter(currentFile)) {
					files.add(currentFile);
				}
			} else {
				recursiveSearchFiles(currentFile);
			}
		}
	}

	private StringBuilder getFileText(File file) {
		BufferedReader bufferedReader = null;
		StringBuilder out = new StringBuilder();
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String current = null;
			while ((current = bufferedReader.readLine()) != null) {
				out.append(current).append("\n");
				current = null;
			}
		} catch (Exception e) {

		} finally {
			closeBR(bufferedReader);
		}
		return out;
	}

	private void searchWords(StringBuilder in, String searchWords,
			Map<String, Integer> map, String filePath,
			Integer searchMethodWord, Boolean isCaseSensetive) {
		String[] split = in.toString().split("[\\s]");
		for (String current : split) {
			if (searchMethodWord.equals(EXACT_WORD)) {
				if (isCaseSensetive) {
					if (current.equals(searchWords)) {
						core(map, filePath);
					}
				} else {
					if (current.equalsIgnoreCase(searchWords)) {
						core(map, filePath);
					}
				}

			} else if (searchMethodWord.equals(EXACT_AND_CONTENT_WORD)) {
				searchWordsSubString(in, searchWords, map, filePath,
						searchMethodWord, current, isCaseSensetive);
			}
		}
	}

	private void searchWordsSubString(StringBuilder in, String searchWords,
			Map<String, Integer> map, String filePath,
			Integer searchMethodWord, String current, Boolean isCaseSensetive) {
		if (isCaseSensetive) {
			if (current.contains(searchWords)) {
				core(map, filePath);
				current = current.substring(current.indexOf(searchWords)
						+ searchWords.length(), current.length());
				if (current.equals("")) {
					return;
				}
				searchWordsSubString(in, searchWords, map, filePath,
						searchMethodWord, current, isCaseSensetive);
			}
		} else {
			if (current.toLowerCase().contains(searchWords.toLowerCase())) {
				core(map, filePath);
				current = current.toLowerCase().substring(current.toLowerCase().indexOf(searchWords.toLowerCase())
						+ searchWords.length(), current.length());
				if (current.equals("")) {
					return;
				}
				searchWordsSubString(in, searchWords, map, filePath,
						searchMethodWord, current, isCaseSensetive);
			}
		}

	}

	private void core(Map<String, Integer> map, String filePath) {
		if (map.containsKey(filePath)) {
			map.put(filePath, map.get(filePath) + 1);
		} else {
			map.put(filePath, 1);
		}
	}
}