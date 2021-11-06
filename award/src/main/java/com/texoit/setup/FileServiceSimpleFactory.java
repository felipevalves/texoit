package com.texoit.setup;

public class FileServiceSimpleFactory {

    private FileServiceSimpleFactory() {
    }

    public static FileService create() {
        return new CsvServiceImpl();
    }
}
