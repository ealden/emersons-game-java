package com.escanan.ealden.race;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class EmersonsGame {
    private static final String CONTEXT_PATH = "/";
    private static final String WEBAPP_DIR = "src/main/webapp";
    private static final String WEBAPP_DIR_PATH = absolutePathOf(WEBAPP_DIR);
    private static final String WEBAPP_MOUNT = "/WEB-INF/classes";
    private static final String CLASSES_DIR = "build/classes";
    private static final String CLASSES_DIR_PATH = absolutePathOf(CLASSES_DIR);
    private static final String INTERNAL_PATH = "/";

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        StandardContext ctx = (StandardContext) tomcat.addWebapp(CONTEXT_PATH, WEBAPP_DIR_PATH);

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, WEBAPP_MOUNT, CLASSES_DIR_PATH, INTERNAL_PATH));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }

    private static String absolutePathOf(String file) {
        return new File(file).getAbsolutePath();
    }
}
