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
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.burdetteportal.BurdettePortalConstants;
import org.openmrs.util.Security;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The main controller.
 */
@Controller
public class BurdettePortalManageController {

    protected final Log log = LogFactory.getLog(getClass());

    @ModelAttribute("url")
    public String getURL() {
        return Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_URL);
    }

    @ModelAttribute("username")
    public String getUsername() {
        return Context.getAdministrationService().getGlobalProperty(BurdettePortalConstants.GP_USERNAME);
    }

    @RequestMapping(value = "/module/burdetteportal/manage", method = RequestMethod.GET)
    public void manage() {
    }

    @RequestMapping(value = "/module/burdetteportal/manage", method = RequestMethod.POST)
    public void setValues(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("url") String url,
                          WebRequest request,
                          ModelMap modelMap) {
        setGP(BurdettePortalConstants.GP_URL, url);
        setGP(BurdettePortalConstants.GP_USERNAME, username);
        setGP(BurdettePortalConstants.GP_PASSWORD, Security.encrypt(password));

        request.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Settings changed.", WebRequest.SCOPE_SESSION);

        modelMap.put("url", getURL());
        modelMap.put("username", getUsername());
    }

    private void setGP(String property, String value) {
        GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(property);
        gp.setPropertyValue(value);
        Context.getAdministrationService().saveGlobalProperty(gp);
    }
}
