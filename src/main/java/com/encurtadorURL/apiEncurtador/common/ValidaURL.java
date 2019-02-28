package com.encurtadorURL.apiEncurtador.common;

import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class ValidaURL {

	public static final ValidaURL INSTANCE = new ValidaURL();
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private ValidaURL() {
    }

    public boolean urlValida(String url) {
        Matcher m = URL_PATTERN.matcher(url);
        return m.matches();
    }
    
}
