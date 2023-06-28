package com.sbz.bank.config;

import java.io.IOException;
import java.nio.file.Paths;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class KieConfig {

	private static final String RULES_PATH = "/rules";

	@Bean
	public KieContainer kieContainer(){
		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		for (Resource file : getRulesFiles()) {
			kieFileSystem.write(ResourceFactory.newClassPathResource(Paths.get(RULES_PATH, file.getFilename()).toString(), "UTF-8"));
		}

		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		KieModule kieModule = kieBuilder.getKieModule();
		KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		// comment the following line to avoid getting infinite warnings
		//kieServices.newKieScanner(kieContainer).start(2000);
		return kieContainer;

	}

	private Resource[] getRulesFiles() {
		try{
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
		}
		catch (IOException e){

		}
		return null;
	}
}
