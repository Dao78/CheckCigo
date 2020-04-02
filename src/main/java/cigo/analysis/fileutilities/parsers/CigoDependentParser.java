package cigo.analysis.fileutilities.parsers;

import java.util.Optional;

import cigo.analysis.fileutilities.parsers.element.CigoDependent_FileElement;

public class CigoDependentParser implements IDependentFileParser<CigoDependent_FileElement>{

	@Override
	public Optional<CigoDependent_FileElement> process(String[] split) {
		if (split == null) return Optional.empty();
		if (split.length != 2) {
			LOGGER.warn("Wrong number of ; {}",split.toString());
			return Optional.empty();
		}
		return Optional.ofNullable(new CigoDependent_FileElement(split));
	}
}
