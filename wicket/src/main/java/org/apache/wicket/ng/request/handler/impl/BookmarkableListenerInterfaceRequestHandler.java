/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.ng.request.handler.impl;

import org.apache.wicket.ng.request.component.IRequestableComponent;
import org.apache.wicket.ng.request.component.IRequestablePage;
import org.apache.wicket.ng.request.component.PageParametersNg;
import org.apache.wicket.ng.request.cycle.RequestCycle;
import org.apache.wicket.ng.request.handler.IComponentRequestHandler;
import org.apache.wicket.ng.request.handler.IPageRequestHandler;
import org.apache.wicket.ng.request.handler.PageAndComponentProvider;
import org.apache.wicket.ng.request.listener.RequestListenerInterface;
import org.apache.wicket.util.lang.Checks;

/**
 * Request handler for bookmarkable pages with listener interface. This handler is only used to
 * generate URLs. Rendering is always handled by {@link ListenerInterfaceRequestHandler}.
 * 
 * @author Matej Knopp
 */
public class BookmarkableListenerInterfaceRequestHandler
	implements
		IPageRequestHandler,
		IComponentRequestHandler
{
	private final PageAndComponentProvider pageComponentProvider;

	private final RequestListenerInterface listenerInterface;

	private final Integer behaviorIndex;

	/**
	 * Construct.
	 * 
	 * @param pageComponentProvider
	 * @param listenerInterface
	 * @param behaviorIndex
	 */
	public BookmarkableListenerInterfaceRequestHandler(
		PageAndComponentProvider pageComponentProvider, RequestListenerInterface listenerInterface,
		Integer behaviorIndex)
	{
		Checks.argumentNotNull(pageComponentProvider, "pageComponentProvider");
		Checks.argumentNotNull(listenerInterface, "listenerInterface");

		this.pageComponentProvider = pageComponentProvider;
		this.listenerInterface = listenerInterface;
		this.behaviorIndex = behaviorIndex;
	}

	/**
	 * Construct.
	 * 
	 * @param pageComponentProvider
	 * @param component
	 * @param listenerInterface
	 */
	public BookmarkableListenerInterfaceRequestHandler(
		PageAndComponentProvider pageComponentProvider, RequestListenerInterface listenerInterface)
	{
		this(pageComponentProvider, listenerInterface, null);
	}

	/**
	 * @see org.apache.wicket.ng.request.handler.IComponentRequestHandler#getComponent()
	 */
	public IRequestableComponent getComponent()
	{
		return pageComponentProvider.getComponent();
	}

	/**
	 * @see org.apache.wicket.ng.request.handler.IPageRequestHandler#getPage()
	 */
	public IRequestablePage getPage()
	{
		return pageComponentProvider.getPageInstance();
	}

	/**
	 * @see org.apache.wicket.ng.request.handler.IPageClassRequestHandler#getPageClass()
	 */
	public Class<? extends IRequestablePage> getPageClass()
	{
		return pageComponentProvider.getPageClass();
	}

	/**
	 * @see org.apache.wicket.ng.request.handler.IPageClassRequestHandler#getPageParameters()
	 */
	public PageParametersNg getPageParameters()
	{
		return pageComponentProvider.getPageParameters();
	}

	/**
	 * @see org.apache.wicket.ng.request.IRequestHandler#detach(org.apache.wicket.ng.request.cycle.RequestCycle)
	 */
	public void detach(RequestCycle requestCycle)
	{
		pageComponentProvider.detach();
	}

	/**
	 * Returns the listener interface.
	 * 
	 * @return listener interface
	 */
	public RequestListenerInterface getListenerInterface()
	{
		return listenerInterface;
	}

	/**
	 * Returns index of behavior this listener is targeted on or <code>null</code> if component is
	 * the target
	 * 
	 * @return behavior index or <code>null</code>
	 */
	public Integer getBehaviorIndex()
	{
		return behaviorIndex;
	}

	/**
	 * @see org.apache.wicket.ng.request.IRequestHandler#respond(org.apache.wicket.ng.request.cycle.RequestCycle)
	 */
	public void respond(RequestCycle requestCycle)
	{
		// nothing to do here, this handler is only used to generate URLs
	}
}
