package com.example.jpa.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Step {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String description;
    boolean wasItSuccessful;

    @ManyToOne
    @JoinColumn
    private TestCase testCase;

    /*
    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL)
    private Set<InputData> inputData;

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL)
    private Set<Bug> bug;
*/

    public Step() {
    }

    public Step(Integer id, boolean wasItSuccessful, TestCase testCase/*, Set<InputData> inputData, Set<Bug> bug*/) {
        this.id = id;
        this.wasItSuccessful = wasItSuccessful;
        this.testCase = testCase;
        //this.inputData = inputData;
        //this.bug = bug;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getWasItSuccessful() {
        return this.wasItSuccessful;
    }

    public void setWasItSuccessful(boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
    }

    public TestCase getTestCase() {
        return this.testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
/*
    public Set<InputData> getInputData() {
        return this.inputData;
    }

    public void setInputData(Set<InputData> inputData) {
        this.inputData = inputData;
    }
    public Set<Bug> getBug() {
        return this.bug;
    }

    public void setBug(Set<Bug> bug) {
        this.bug = bug;
    }
*/
    public Step id(Integer id) {
        this.id = id;
        return this;
    }

    public Step wasItSuccessful(boolean wasItSuccessful) {
        this.wasItSuccessful = wasItSuccessful;
        return this;
    }

    public Step testCase(TestCase testCase) {
        this.testCase = testCase;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Step)) {
            return false;
        }
        Step step = (Step) o;
        return Objects.equals(id, step.id) && wasItSuccessful == step.wasItSuccessful && Objects.equals(testCase, step.testCase) /*&& Objects.equals(inputData, step.inputData) && Objects.equals(bug, step.bug)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wasItSuccessful, testCase/*, inputData, bug*/);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", wasItSuccessful='" + getWasItSuccessful() + "'" +
            ", testCase='" + getTestCase() + "'" +
            "}";
    }

}