package com.texoit.setup;

import com.texoit.award.entity.Award;
import com.texoit.producer.Producer;

import java.io.IOException;
import java.util.List;

public interface FileService {

    void downloadFile(String url, String destination) throws IOException;
    List<List<String>> readFile(String filename) throws IOException;

    List<Producer> getListProducer(List<String> line);
    List<Award> getListAwards(List<Producer> listAllProducer, List<String> line);

}
