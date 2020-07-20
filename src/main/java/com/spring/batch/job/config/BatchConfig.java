package com.spring.batch.job.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring.batch.job.model.TransactionRepository;
import com.spring.batch.job.model.bean.Transaction;
import com.spring.batch.job.model.dto.BankTransactionDto;
import com.spring.batch.job.model.service.ExportFileService;
import com.spring.batch.job.processor.TransactionItemProcessor;
import com.spring.batch.job.task.ReadFileTask;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

    @Autowired
    EntityManagerFactory emf;
 
    @Autowired
    TransactionRepository transactionRepository;
    
	@Bean
	public FlatFileItemReader<BankTransactionDto> reader() {
		FlatFileItemReader<BankTransactionDto> reader = new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource("input/dataSource.txt"));
		reader.setLinesToSkip(1);

		DefaultLineMapper<BankTransactionDto> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("|");
		tokenizer.setNames("ACCOUNT_NUMBER", "TRX_AMOUNT", "DESCRIPTION", "TRX_DATE", "TRX_TIME", "CUSTOMER_ID");

		BeanWrapperFieldSetMapper<BankTransactionDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(BankTransactionDto.class);

		lineMapper.setFieldSetMapper(fieldSetMapper);
		lineMapper.setLineTokenizer(tokenizer);
		reader.setLineMapper(lineMapper);

		return reader;

	}

	@Bean
	public ItemProcessor<BankTransactionDto, Transaction> processor() {
		return new TransactionItemProcessor();
	}
	
    @Bean
    public JpaItemWriter<Transaction> writer() {
        JpaItemWriter<Transaction> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
    
    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
            	System.out.println("Let's get started!");
            }
 
            @Override
            public void afterJob(JobExecution jobExecution) {            	
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//                    System.out.println("!!! JOB FINISHED! Time to verify the results");
//                    transactionRepository.findAll().
//                            forEach(transaction -> System.out.println("Found <" + transaction.toString() + "> in the database."));
//                    
                    try {
						ExportFileService.exportToExcel(transactionRepository.findAll());
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
            }
        };
    }
	
	@Bean
	public Step step1() {
		return steps.get("step1").tasklet(new ReadFileTask()).build();
	}

	@Bean
	public Step step2() {
		return steps.get("step2")
				.<BankTransactionDto, Transaction>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public Job demoJob() {
		return jobs.get("demoJob").incrementer(new RunIdIncrementer()).listener(listener()).start(step2()).build();
	}

}
