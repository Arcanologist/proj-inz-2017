package com.my.app.web.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class RegMatcher extends TypeSafeMatcher<String> {

	private final String regex;

	public RegMatcher(final String regex) {
		this.regex = regex;
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText("matches regex=`" + regex + "`");
	}

	@Override
	public boolean matchesSafely(final String string) {
		return string.matches(regex);
	}

	public static RegMatcher matchesRegex(final String regex) {
		return new RegMatcher(regex);
	}
}
