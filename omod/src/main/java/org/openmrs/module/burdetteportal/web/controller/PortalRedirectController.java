/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.burdetteportal.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.burdetteportal.BurdettePortalConstants;
import org.openmrs.util.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PortalRedirectController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/burdetteportal/portal.htm", method = RequestMethod.GET)
	public void redirect(HttpServletResponse response) {
		User user = Context.getAuthenticatedUser();

        // get the url, username and password
        String url = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_URL);
        String username = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_USERNAME);
        String password = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_PASSWORD);

        // TODO send a real response with a helpful UI message
        if (url == null || username == null || password == null) {
            throw new APIException("URL, username or password not configured yet.");
        }

        // password should be encoded; decode it
        password = Security.decrypt(password);

        // using GET, everything is in the request
        url += "?username=" + username;
        url += "&password=" + password;
        url += "&omrs_user=" + user.getUsername();

        try {
			response.sendRedirect(url);
		} catch (IOException e) {
			throw new APIException("cannot redirect.", e);
		}
	}

	private void respondUsingPOST(HttpServletRequest request, HttpServletResponse response, User user) {

        // get the url, username and password
        String url = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_URL);
        String username = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_USERNAME);
        String password = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_PASSWORD);

        // TODO send a real response with a helpful UI message
        if (url == null || username == null || password == null) {
            throw new APIException("URL, username or password not configured yet.");
        }

        // password should be encoded; decode it
        password = Security.decrypt(password);

        // session attributes may be useful later
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("omrs_user", user.getUsername());

		RequestDispatcher rd = request.getRequestDispatcher(url);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			throw new APIException("cannot redirect.", e);
		} catch (IOException e) {
			throw new APIException("cannot redirect.", e);
		}
	}

	private void respondUsingGET(HttpServletRequest request, HttpServletResponse response, User user) {
		HttpSession session = request.getSession();

        // get the url, username and password
        String url = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_URL);
        String username = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_USERNAME);
        String password = Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_PASSWORD);

        // TODO send a real response with a helpful UI message
        if (url == null || username == null || password == null) {
            throw new APIException("URL, username or password not configured yet.");
        }

        // password should be encoded; decode it
        password = Security.decrypt(password);

        // session attributes may be useful later
        session.setAttribute("username", username);
		session.setAttribute("password", password);
		session.setAttribute("omrs_user", user.getUsername());

        // using GET, everything is in the request
		url += "?username=" + username;
		url += "&password=" + password;
		url += "&omrs_user=" + user.getUsername();

		log.info("Setting omrs_user to: " + user.getUsername());

		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			throw new APIException("cannot redirect.", e);
		}
	}
}
