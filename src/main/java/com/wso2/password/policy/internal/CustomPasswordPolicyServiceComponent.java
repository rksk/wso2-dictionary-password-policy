package com.wso2.password.policy.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;

/**
 * @scr.component name="com.wso2.password.policy"
 * immediate="true"
 */

public class CustomPasswordPolicyServiceComponent {
    private static Log log = LogFactory.getLog(CustomPasswordPolicyServiceComponent.class);


    protected void activate(ComponentContext context) {

        log.info("Custom Password Pattern Policy Extension bundle is activated");
    }


    protected void deactivate(ComponentContext context) {
        log.info("Custom Password Pattern Policy Extension bundle is is de-activated");
    }

}
