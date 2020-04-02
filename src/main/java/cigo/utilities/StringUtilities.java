package cigo.utilities;

public class StringUtilities {
	public static boolean isEmpty(String string) {
		if (string == null)
			return true;
		string = string.trim();
		if (string.length() == 0)
			return true;
		return false;
	}
}

