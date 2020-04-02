package cigo.analysis.fileutilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cigo.app.IFileConstants;
import cigo.utilities.StringUtilities;

public class ReadDependentFileList {
	private static final Logger LOGGER = LogManager.getLogger("readfile");
	
	public static List<DependentFileElement> readFile(String filename, boolean readOfficeInfo) {
		InputStream fileinputStream = ReadDependentFileList.class.getClassLoader().getResourceAsStream(filename);
		if (fileinputStream == null) {
			LOGGER.error("Can t read {}", filename);
			return new ArrayList<>();
		}
		List<DependentFileElement> dependentList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileinputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (StringUtilities.isEmpty(line)) continue;
				String[] split = line.split(";");
				if (split == null) continue;
				if (!readOfficeInfo) {
					if (split.length != 2) {
						LOGGER.warn(line + " Too many ;");
						continue;
					}
				} else {
					if (split.length != 3) {
						LOGGER.warn(line + " Too many ;");
						continue;
					}
				}
				DependentFileElement dep = new DependentFileElement(split[0], split[1]);
				if (readOfficeInfo) {
					dep.setOfficeInfo(split[2]);
				}
				dependentList.add(dep);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dependentList;
	}
}
