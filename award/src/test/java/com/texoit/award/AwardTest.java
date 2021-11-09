package com.texoit.award;

import com.texoit.award.entity.Award;
import com.texoit.award.entity.Movie;
import com.texoit.producer.Producer;
import com.texoit.producer.ProducerService;
import com.texoit.setup.FileService;
import com.texoit.setup.FileServiceSimpleFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AwardTest {

//	@Value("${url.movie.list}")
//	private String url;
	@Value("${filename}")
	private String filename;

	@Autowired
	private AwardService awardService;

	private FileService fileService = FileServiceSimpleFactory.create();

	@BeforeEach
	void setup() throws IOException {

//		fileService.downloadFile(url, filename);
	}

	@Test
	void test_verify_saved_award() throws IOException {

		var listAwardFile = getListAward();
		var listAwardH2 = awardService.findAll();
		var moviesH2 = listAwardH2.stream().map(Award::getMovie).distinct().toList();
		var moviesFile = listAwardFile.stream().map(Award::getMovie).distinct().toList();

		Assertions.assertEquals(moviesH2.size(), moviesFile.size(), "Both lists size should be equal");

	}

	private List<Award> getListAward() throws IOException {
		List<List<String>> listData = fileService.readFile(filename);

		List<Award> listAwardFile = new ArrayList<>();
		List<Producer> listProducerFile = new ArrayList<>();

		listData.forEach(line -> {
			List<Producer> list = fileService.getListProducer(line);
			list.removeAll(listProducerFile);
			listProducerFile.addAll(list);

			listAwardFile.addAll(fileService.getListAwards(listProducerFile, line));
		});

		return listAwardFile;
	}

}
