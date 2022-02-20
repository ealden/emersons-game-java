package com.escanan.ealden.race;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class EmersonsGame {
    private static final String PORT = System.getenv("PORT");
    private static final String CONTEXT_PATH = "";
    private static final String WEBAPP_DIR = "src/main/webapp";
    private static final String WEBAPP_MOUNT = "/WEB-INF/classes";
    private static final String CLASSES_DIR = "build/classes";
    private static final String INTERNAL_PATH = "/";

    private TomcatContainer container;

    public static void main(String[] args) {
        Configurations.raceService().newRace();

        EmersonsGame application = new EmersonsGame();
        application.listen();
    }

    public EmersonsGame() {
        container = new TomcatContainer();

        if (PORT != null) {
            container.setPort(PORT);
        }

        container.addWebApp(CONTEXT_PATH, WEBAPP_DIR, WEBAPP_MOUNT, CLASSES_DIR, INTERNAL_PATH);
    }

    public void start() {
        container.start();
    }

    public void listen() {
        container.listen();
    }

    public void testMode() {
        Configurations.setTestMode(true);
    }

    public static class TomcatContainer {
        private Tomcat tomcat;

        public TomcatContainer() {
            tomcat = new Tomcat();
        }

        public void setPort(int port) {
            tomcat.setPort(port);
        }

        public void setPort(String port) {
            setPort(Integer.parseInt(port));
        }

        public void addWebApp(String contextPath, String webAppDir, String webAppMount, String classesDir, String internalPath) {
            String docBase = new File(webAppDir).getAbsolutePath();
            String base = new File(classesDir).getAbsolutePath();

            StandardContext ctx = (StandardContext) tomcat.addWebapp(contextPath, docBase);

            WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(new DirResourceSet(resources, webAppMount, base, internalPath));
            ctx.setResources(resources);
        }

        public void start() {
            try {
                tomcat.start();
            } catch (LifecycleException e) {
                throw new StartupException(e);
            }
        }

        public void listen() {
            start();

            tomcat.getServer().await();
        }

        private static class StartupException extends RuntimeException {
            public StartupException(Throwable cause) {
                super(cause);
            }
        }
    }
}
