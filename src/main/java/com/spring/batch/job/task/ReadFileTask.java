package com.spring.batch.job.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ReadFileTask implements Tasklet{

	@Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception 
    {
        System.out.println("MyTaskOne start..");
 
         
        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
    }  
    
}
