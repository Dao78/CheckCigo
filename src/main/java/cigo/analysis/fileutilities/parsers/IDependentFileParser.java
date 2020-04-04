package cigo.analysis.fileutilities.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cigo.analysis.fileutilities.parsers.element.AbstractDependent_FileElement;
import cigo.app.IFileConstants;
import cigo.utilities.StringUtilities;

public interface IDependentFileParser<T extends AbstractDependent_FileElement> {
	public static final Logger LOGGER = LogManager.getLogger("readfile");

	Optional<T> process(String[] split);

	
	default List<T> readFile(String filename) {
		InputStream fileinputStream = IDependentFileParser.class.getClassLoader().getResourceAsStream(filename);
		if (fileinputStream == null) {
			LOGGER.error("Can t read {}", filename);
			return new ArrayList<>();
		}
		List<T> dependentList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileinputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (StringUtilities.isEmpty(line)) continue;
				String[] split = line.split(";");
				Optional<T> process = process(split);
				if (process.isPresent()) dependentList.add(process.get());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dependentList;
	}
	
	public static List<String> getAllCigoFiles() {
		URL resourceFolder = IDependentFileParser.class.getClassLoader().getResource(IFileConstants.FILE_FOLDER);
		String path = resourceFolder.getPath();
		return Arrays.asList(new File(path).listFiles()).stream().filter(f -> f.getName().startsWith(IFileConstants.FILEPREFIX)).map(File::getName).collect(Collectors.toList());
	}
}
