package ru.amlet.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Date;

import static ru.amlet.config.JobConfig.IMPORT_LIBRARY_JOB_NAME;

@ShellComponent
@RequiredArgsConstructor
public class BatchCommands {

    public static final String PARAMETER_KEY = "currentDate";
    private final Job importUserJob;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .addDate(PARAMETER_KEY, new Date())
                .toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_LIBRARY_JOB_NAME));
    }
}
