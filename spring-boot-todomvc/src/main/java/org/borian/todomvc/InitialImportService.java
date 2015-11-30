package org.borian.todomvc;

import org.borian.todomvc.model.Todo;
import org.borian.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * A service that is responsible to setup the database with initial test data.
 */
@Component
public class InitialImportService {


    @Autowired
    TodoRepository todoRepository;

    @PostConstruct
    private void init() {
        todoRepository.save(new Todo("Finish Docker Workshop", false));
        todoRepository.save(new Todo("run docker inside docker", false));
    }

}
