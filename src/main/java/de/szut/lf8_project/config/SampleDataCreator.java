package de.szut.lf8_project.config;

import de.szut.lf8_project.project.ProjectEntity;
import de.szut.lf8_project.project.ProjectRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataCreator implements ApplicationRunner {

    private ProjectRepository repository;

    public SampleDataCreator(ProjectRepository repository) {
        this.repository = repository;
    }

    public void run(ApplicationArguments args) {
        repository.save(new ProjectEntity("Projekt One", "Nachhaltigkeit"));
        repository.save(new ProjectEntity("Projekt Two", "ESG"));
    }

}
