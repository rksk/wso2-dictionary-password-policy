package com.wso2.password.policy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.mgt.policy.AbstractPasswordPolicyEnforcer;

import java.util.ArrayList;
import java.util.Map;

/**
 * Custom password policy extension
 */

public class CustomPasswordPatternPolicy extends AbstractPasswordPolicyEnforcer {

    private static final Log log = LogFactory.getLog(CustomPasswordPatternPolicy.class);
    private ArrayList<String> dictionaryWords = new ArrayList<String>();
    private String errorResponse;

    /**
     * Validates the password against the pattern provided
     *
     * @param args - comes as object array, contains the username and the password.
     * @return boolean
     */
    public boolean enforce(Object... args) {

        boolean status = true;

        if (args != null) {

            String password = args[0].toString();
            String userName = args[1].toString();

            // Info log added for demo purpose
            log.info("Validating password pattern for user : " + userName);


            if (validatePasswordPattern(password)) {
                log.info("Password complies with the policy !"); // Info log for demo purpose

                status = true;
            } else {
                log.info("Password violates the policy !"); // Info log for demo purpose

                // errorMessage is the attribute defined in the parent class for sending the error message to outside
                errorMessage = errorResponse;

                status = false;
            }
        }

        return status;
    }

    /**
     * Load the extension while startup
     *
     * @param parameters contains values related to this extension define in the identity-mgt.properties
     */
    public void init(Map<String, String> parameters) {

        if (parameters != null && parameters.size() > 0) {
            // Parameters related to this extension defined in identity-mgt.properties file
            if (StringUtils.isNotEmpty(parameters.get("errorResponse"))) {
                errorResponse = parameters.get("errorResponse");
            }
        }

        initializeDictionaryWordsList();
    }

    /**
     * Sample method for creating a list of dictionary words
     */
    private void initializeDictionaryWordsList() {
        // initialize sample dictionary words list
        dictionaryWords.add("hello");
        dictionaryWords.add("world");
    }


    /**
     * Method to validate password against the policy
     *
     * @param password - user's password
     * @return true if pattern matched, false if pattern not matched
     */
    private boolean validatePasswordPattern(String password) {

        for (String pwd : dictionaryWords) {

            if (password.contains(pwd)) {
                // password contains this dictionary word
                return false;
            }
        }

        // password does not contain any word in the defined dictionary
        return true;
    }

}
