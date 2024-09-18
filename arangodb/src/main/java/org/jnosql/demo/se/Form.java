package org.jnosql.demo.se;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Map;

@Entity
public class Form {

    @Id
    private String id;

    @Column
    private String option;

    @Column
    private Map<String, Object> questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Object> questions) {
        this.questions = questions;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id='" + id + '\'' +
                ", option='" + option + '\'' +
                ", questions=" + questions +
                '}';
    }
}
