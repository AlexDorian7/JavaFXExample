package team.logica_populi.javafxdemo.xml.properties.transformers;

import java.io.File;

public class FileTransformer implements Transformer<File> {
    public static final FileTransformer transformer = new FileTransformer();

    @Override
    public String toString(File value) {
        return value.toString();
    }

    @Override
    public File fromString(String str) {
        return new File(str);
    }
}
