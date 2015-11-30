package org.borian.todomvc.repository;

import org.borian.todomvc.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "todo", path = "todos")
public interface TodoRepository extends CrudRepository<Todo,Long> {

    Todo findTodoByTitle(@Param("title") String title);

}
