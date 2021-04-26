package com.escanan.ealden.race;

import com.escanan.ealden.race.service.impl.RaceServiceImpl;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class EmersonsGame {
    private static final int PORT = 8080;

    private static final String CONTEXT_PATH = "/";
    private static final String WEBAPP_DIR = "src/main/webapp";
    private static final String WEBAPP_DIR_PATH = absolutePathOf(WEBAPP_DIR);
    private static final String WEBAPP_MOUNT = "/WEB-INF/classes";
    private static final String CLASSES_DIR = "build/classes";
    private static final String CLASSES_DIR_PATH = absolutePathOf(CLASSES_DIR);
    private static final String INTERNAL_PATH = "/";

    private Tomcat tomcat;

    public static void main(String[] args) {
        RaceServiceImpl.INSTANCE.newRace();

        EmersonsGame application = new EmersonsGame();
        application.start();
        application.await();
    }

    private static String absolutePathOf(String file) {
        return new File(file).getAbsolutePath();
    }

    public void start() {
        tomcat = new Tomcat();
        tomcat.setPort(PORT);

        StandardContext ctx = (StandardContext) tomcat.addWebapp(CONTEXT_PATH, WEBAPP_DIR_PATH);

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, WEBAPP_MOUNT, CLASSES_DIR_PATH, INTERNAL_PATH));
        ctx.setResources(resources);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    public void await() {
        tomcat.getServer().await();
    }

    public void testMode() {
        Configurations.setTestMode(true);
    }
}
