package team.logica_populi.javafxdemo.xml.properties;


import team.logica_populi.javafxdemo.xml.properties.transformers.FileTransformer;

import java.io.File;

public class FileProperty extends Property<File> {
    public FileProperty(String name, File defaultValue) {
        this(name, defaultValue, false);
    }

    public FileProperty(String name, File defaultValue, boolean optional) {
        super(name, defaultValue, FileTransformer.transformer, optional);
    }
}
