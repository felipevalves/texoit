package com.texoit.award;

import com.texoit.award.entity.Award;
import com.texoit.award.entity.Producer;
import com.texoit.award.repository.MovieRepository;
import com.texoit.award.repository.ProducerRepository;
import com.texoit.award.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetupApplication implements ApplicationRunner {

    @Autowired
    private ProducerRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Value("${url.movie.list}")
    private String url;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("ProducerService.run");

        FileUtils.copyURLToFile(
                new URL(url),
                new File("movie-list.csv"),
                1000 * 3,
                1000 * 5);


        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("movie-list.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String [] values = line.split(";");

                records.add(List.of(values));
            }
        }

        List<Producer> listAllProducer = new ArrayList<>();

        records.forEach(values -> {
            log.info(String.valueOf(values));
            updateProducers(listAllProducer, values);
            updateMovies(listAllProducer, values);
            log.info(String.valueOf(listAllProducer));
        });

        repository.saveAll(listAllProducer);

        List<Producer> list = repository.findAll();
        log.info("List producers " + list);
    }

    private void updateProducers(List<Producer> listAllProducer, List<String> line) {

        if (line.get(Constant.POS_PRODUCER.getKey()) == null)
            return;

        List<Producer> list = getProducers(line.get(Constant.POS_PRODUCER.getKey()));
        list.removeAll(listAllProducer);
        listAllProducer.addAll(list);
    }

    private List<Producer> getProducers(String producers) {
        var arrayProducers = producers.split(",|and");

        return Arrays.stream(arrayProducers)
                .map( s -> new Producer(s.trim()))
                .collect(Collectors.toList());
    }

    private void updateMovies(List<Producer> listAllProducer, List<String> line) {

        if (getYear(line) == -1 ||
                getMovie(line) == null )
            return;

        Integer year = getYear(line);
        String movie = getMovie(line);
        boolean winner = getWinner(line);

        var lineProducers = getProducers(line.get(Constant.POS_PRODUCER.getKey()));

        listAllProducer.stream().filter(producer ->
                lineProducers.stream().anyMatch(lineProducer -> lineProducer.getName().equals(producer.getName()))
        ).forEach(p -> p.addAward(new Award(year, movie, winner)));
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
