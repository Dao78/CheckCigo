package cigo.analysis.fileutilities.parsers.element;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public abstract class AbstractDependent_FileElement {
	protected String surname;
	protected String name;
	
	public AbstractDependent_FileElement(String surname, String name) {
		this.surname = surname != null ? surname.trim() : null;
		this.name    = name    != null ? name.trim()    : null;
	}
	
	protected String getStringValue(String splittedString) {
		return splittedString != null ? splittedString.trim() : null;
	}
	
	@Override
	public String toString() {
		return surname + " " + name;
	}
}
