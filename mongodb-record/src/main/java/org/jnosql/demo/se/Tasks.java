package org.jnosql.demo.se;

import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.List;
import java.util.Map;

@Repository
public interface Tasks {

    @Save
    Task save(Task task);

    @Query("from Task where enabled = true")
    List<Task> listAllEnabled();

    @Query("from Task where enabled = false")
    List<Task> listAllDisabled();

    @Find
    List<Task> findAll();

    @Delete
    void remove(Task task);
}
