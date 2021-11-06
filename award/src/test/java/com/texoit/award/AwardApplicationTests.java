package com.texoit.award;

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
class AwardApplicationTests {

	@Value("${url.movie.list}")
	private String url;
	@Value("${filename}")
	private String filename;

	@Autowired
	private ProducerService producerService;

	private FileService fileService = FileServiceSimpleFactory.create();

	@BeforeEach
	void setup() throws IOException {

		fileService.downloadFile(url, filename);
	}

	@Test
	void test_verify_saved_producer() throws IOException {

		List<List<String>> listData = fileService.readFile(filename);

		List<Producer> listProducerFile = new ArrayList<>();

		listData.forEach(line -> {
			List<Producer> list =fileService.getListProducer(line);
			list.removeAll(listProducerFile);
			listProducerFile.addAll(list);
		});

		List<Producer> listProducerH2 = producerService.findAll();

		Assertions.assertEquals(listProducerH2.size(), listProducerFile.size(), "Both lists size should be equal");
		System.out.println(listProducerFile.size() + " " + listProducerH2.size());

	}

}
