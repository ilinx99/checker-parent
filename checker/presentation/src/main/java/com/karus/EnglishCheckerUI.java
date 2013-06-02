/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.karus;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.annotations.Theme;
import com.vaadin.server.DefaultErrorListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Terminal;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServiceSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
@Scope("prototype")
@Theme(ChameleonTheme.THEME_NAME)
public class EnglishCheckerUI extends UI implements Terminal.ErrorListener{
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
        
        VaadinServiceSession.getCurrent().setErrorHandler(this);
        setSizeFull();
        
        try
        {
        	DiscoveryNavigator navigator = new DiscoveryNavigator(this, getContent());
            navigator.navigateTo(UI.getCurrent().getPage().getFragment());
        }
        /**
         * Exception on page load
         */
        catch (AccessDeniedException e)
        {
            Label label = new Label(e.getMessage());
            label.setWidth(-1, Unit.PERCENTAGE);

            Link goToMain = new Link("Go to login page", new ExternalResource("/login/"));

            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(label);
            layout.addComponent(goToMain);
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            layout.setComponentAlignment(goToMain, Alignment.MIDDLE_CENTER);

            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setSizeFull();
            mainLayout.addComponent(layout);
            mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

            setContent(mainLayout);
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
	}


    @Override
    public void terminalError(Terminal.ErrorEvent event)
    {
        if (event.getThrowable().getCause() instanceof AccessDeniedException)
        {
            AccessDeniedException accessDeniedException = (AccessDeniedException) event.getThrowable().getCause();
            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);
            return;
        }

        DefaultErrorListener.doDefault(event);
    }

}
