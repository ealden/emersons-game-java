package com.escanan.ealden.race;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

public class EmersonsGame {
    private static final String PORT = System.getenv("PORT");
    private static final String CONTEXT_PATH = "";
    private static final String WEBAPP_DIR = "src/main/webapp";
    private static final String WEBAPP_MOUNT = "/WEB-INF/classes";
    private static final String CLASSES_DIR = "build/classes";
    private static final String INTERNAL_PATH = "/";

    private Tomcat tomcat;

    public EmersonsGame() {
        configure();
    }

    private void configure() {
        tomcat = new Tomcat();

        if (PORT != null) {
            tomcat.setPort(Integer.parseInt(PORT));
        }

        StandardContext ctx = (StandardContext) tomcat.addWebapp(CONTEXT_PATH, pathTo(WEBAPP_DIR));

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, WEBAPP_MOUNT, pathTo(CLASSES_DIR), INTERNAL_PATH));
        ctx.setResources(resources);
    }

    public void start() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new StartupException(e);
        }
    }

    public void await() {
        tomcat.getServer().await();
    }

    public void testMode() {
        Configurations.setTestMode(true);
    }

    private static class StartupException extends RuntimeException {
        public StartupException(Throwable cause) {
            super(cause);
        }
    }

    private static String pathTo(String file) {
        return new File(file).getAbsolutePath();
    }

    public static void main(String[] args) {
        Configurations.raceService().newRace();

        EmersonsGame application = new EmersonsGame();
        application.start();
        application.await();
    }
}
