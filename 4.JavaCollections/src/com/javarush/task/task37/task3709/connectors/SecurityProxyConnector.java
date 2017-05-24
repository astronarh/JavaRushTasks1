package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

/**
 * Created by ShkerdinVA on 24.05.2017.
 */
public class SecurityProxyConnector implements Connector{
    private SimpleConnector simpleConnector;
    private SecurityChecker securityChecker;

    public SecurityProxyConnector(String string) {
        this.simpleConnector = new SimpleConnector(string);
    }

    public void connect() {
        if (securityChecker.performSecurityCheck()) simpleConnector.connect();
    }
}
