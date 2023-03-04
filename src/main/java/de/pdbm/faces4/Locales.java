package de.pdbm.faces4;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * Available VM locales.
 */
@Named
@RequestScoped
public class Locales {

	/**
	 * Map of locales, keyed by language.
	 * 
	 * @param languages list of langages
	 * @return Map of locales, keyed by language
	 */
	public Map<String, List<Locale>> localesFor(String... languages) {
		Map<String, List<Locale>> availableLocales = Stream.of(Locale.getAvailableLocales())
				.collect(Collectors.groupingBy(Locale::getLanguage));
		return Stream.of(languages).filter(availableLocales::containsKey)
				.collect(Collectors.toMap(Function.identity(), availableLocales::get));
	}

}
