package com.techvisio.einstitution.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.techvisio.einstitution.factory.SequenceFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/Application-context.xml" })

public class SequenceFactoryTest {

	@Autowired
    private SequenceFactory sequenceFactory;
	
	@Test
	public void getSequenceTest(){
		Long seq=sequenceFactory.getSequence("INQUIRY");
		System.out.println(seq);
	}
}
