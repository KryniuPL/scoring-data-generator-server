package com.scoring;

import io.micronaut.runtime.Micronaut;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Application {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        Micronaut.run(Application.class, args);
    }
}
