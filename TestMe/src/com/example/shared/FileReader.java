package com.example.shared;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Alexander Thomas
 * @date 06.11.2016
 */
public class FileReader {

	public static List<String> read(String path) {
		List<String> names = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(names::add);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return names;
	}

}
