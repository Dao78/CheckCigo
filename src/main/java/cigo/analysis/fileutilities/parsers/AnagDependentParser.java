package cigo.analysis.fileutilities.parsers;

import java.util.Optional;

import cigo.analysis.fileutilities.parsers.element.AnagDependent_FileElement;

public class AnagDependentParser implements IDependentFileParser<AnagDependent_FileElement>{

	@Override
	public Optional<AnagDependent_FileElement> process(String[] split) {
		if (split == null) return Optional.empty();
		if (split.length != 4) {
			LOGGER.warn("Wrong number of ; {}",split.toString());
			return Optional.empty();
		}
		return Optional.ofNullable(new AnagDependent_FileElement(split));
	}
}
