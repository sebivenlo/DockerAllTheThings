package org.borian.todomvc.test;

import org.borian.todomvc.Application;
import org.borian.todomvc.model.Todo;
import org.borian.todomvc.repository.TodoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TodoTest {

    @Autowired
    TodoRepository todoRepository;


    @Test
    public void contextLoads() {
        Todo todo = todoRepository.findTodoByTitle("Sample 1");
        Assert.assertEquals(todo.getCompleted(), true);
    }

    @Test
    public void testDelete() {
        Todo todo = todoRepository.findTodoByTitle("Sample 1");
        long count = todoRepository.count();
        todoRepository.delete(todo);
        Assert.assertEquals(count - 1, todoRepository.count());
    }

}
