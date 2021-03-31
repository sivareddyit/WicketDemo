package com.test;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;

public class HelloWorld extends WebPage {

	private static final long serialVersionUID = 1L;

	public HelloWorld() {

		Label label = new Label("message", "Hello World!");
		add(label);

		Label demoLabel = new Label("demo", "demo");
		demoLabel.setVisible(false);
		demoLabel.setOutputMarkupPlaceholderTag(true);
		add(demoLabel);

		Label nameLabel = new Label("name", "Siva");
		nameLabel.setOutputMarkupId(true);
		add(nameLabel);

		add(getLink());

		// Link
		add(getLabel(nameLabel));

		add(getButton(demoLabel));

	}

	private Link<?> getLink() {
		return new Link<String>("pageWithIndexParam") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

				PageParameters pageParameters = new PageParameters();
				pageParameters.add("foo", "foo");
				pageParameters.add("bar", "bar");

				setResponsePage(PageWithParameters.class, pageParameters);
			}

			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				return null;
			}

		};
	}

	private Label getLabel(Label nameLabel) {
		Label ajaxLabel = new Label("ajax", "ajax");
		ajaxLabel.add(new AjaxEventBehavior("click") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				nameLabel.setDefaultModel(Model.of("Siva Kunduru"));
				target.add(nameLabel);
			}
		});
		return ajaxLabel;
	}

	private Button getButton(Label demoLabel) {
		Button button = new Button("button", () -> "Button");
		button.add(new AjaxEventBehavior("click") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				demoLabel.setVisible(true);
				target.add(demoLabel);
				target.appendJavaScript("alert('clicked')");
			}
		});
		return button;
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		ContextRelativeResourceReference resource = new ContextRelativeResourceReference("style.css");
		CssHeaderItem cssItem = CssHeaderItem.forReference(resource);

		response.render(cssItem);
	}
}