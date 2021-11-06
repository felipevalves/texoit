package com.texoit.setup;

import com.texoit.award.entity.Award;
import com.texoit.award.entity.Movie;
import com.texoit.producer.Producer;
import com.texoit.shared.util.Constant;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CsvServiceImpl implements FileService {
    @Override
    public void downloadFile(String url, String destination) throws IOException {
        FileUtils.copyURLToFile(
                new URL(url),
                new File(destination));
    }

    @Override
    public List<List<String>> readFile(String filename) throws IOException {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine();  //header
            while ((line = br.readLine()) != null) {
                String [] values = line.split(";");
                records.add(List.of(values));
            }
        }
        return records;
    }

    @Override
    public List<Producer> getListProducer(List<String> line) {
        if (line.get(Constant.POS_PRODUCER.getKey()) == null)
            return Collections.emptyList();

        var arrayProducers = line.get(Constant.POS_PRODUCER.getKey()).split(",| and ");

        return Arrays.stream(arrayProducers)
                .filter(name -> !name.isBlank())
                .map( s -> new Producer(s.trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Award> getListAwards(List<Producer> listAllProducer, List<String> line) {

        if (getYear(line) == -1 || getMovie(line) == null )
            return Collections.emptyList();

        Integer year = getYear(line);
        String movie = getMovie(line);
        boolean winner = getWinner(line);

        var lineProducers = getListProducer(line);

        return listAllProducer.stream()
                .filter(producer -> lineProducers.stream().anyMatch(lineProducer -> lineProducer.equals(producer)))
                .map(p -> {
                    Award a = new Award(year, new Movie(movie), winner);
                    a.setProducer(p);
                    return a;
                }).collect(Collectors.toList());
    }

    private String getMovie(List<String> line) {
        return line.get(Constant.POS_MOVIE.getKey());
    }

    private Integer getYear(List<String> line) {
        if (line.get(Constant.POS_YEAR.getKey()) == null)
            return -1;
        return Integer.valueOf(line.get(Constant.POS_YEAR.getKey()));
    }

    private boolean getWinner(List<String> line) {

        if (line.size() <= Constant.POS_WINNER.getKey())
            return false;

        return line.get(Constant.POS_WINNER.getKey()).equalsIgnoreCase("yes");
    }
}
