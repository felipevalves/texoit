package com.texoit.setup;

import com.texoit.award.AwardService;
import com.texoit.award.entity.Award;
import com.texoit.producer.Producer;
import com.texoit.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SetupApplication implements ApplicationRunner {

    @Autowired
    private ProducerService producerService;
    @Autowired
    private AwardService awardService;

    private FileService csvService = FileServiceSimpleFactory.create();

    @Value("${url.movie.list}")
    private String url;
    @Value("${filename}")
    private String filename;


    @Override
    public void run(ApplicationArguments args) {

        List<List<String>> listData = handleFile();

        if (listData.isEmpty()) {
            log.info("It's not possible to get data.");
            return;
        }

        prepareAndSaveData(listData);
    }


    private List<List<String>> handleFile() {
        try {
            csvService.downloadFile(url, filename);
            return csvService.readFile(filename);
        } catch (IOException e) {
            log.error("Erro to read file ", e);
            return Collections.emptyList();
        }
    }

    private void prepareAndSaveData(List<List<String>> listData) {

        List<Producer> listAllProducer = new ArrayList<>();
        List<Award> listAward = new ArrayList<>();

        listData.forEach(data -> {
            updateProducers(listAllProducer, data);
            listAward.addAll(getAwards(listAllProducer, data));

        });

        producerService.saveAll(listAllProducer);
        awardService.saveAll(listAward);
    }

    private void updateProducers(List<Producer> listAllProducer, List<String> line) {

        List<Producer> list = csvService.getListProducer(line);
        list.removeAll(listAllProducer);
        listAllProducer.addAll(list);
    }

    private List<Award> getAwards(List<Producer> listAllProducer, List<String> line) {
        return csvService.getListAwards(listAllProducer, line);
    }
}
