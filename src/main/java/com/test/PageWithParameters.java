package com.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PageWithParameters extends WebPage {

	private static final long serialVersionUID = 1L;

	// Override superclass constructor
	public PageWithParameters(PageParameters parameters) {
		super(parameters);
		add(new Label("firstParam", () -> parameters.get("foo")));
		add(new Label("secondParam", () -> parameters.get("bar")));
	}
}