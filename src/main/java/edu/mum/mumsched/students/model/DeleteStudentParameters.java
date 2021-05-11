package edu.mum.mumsched.students.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class DeleteStudentParameters {
    private final Long studentId;

    @JsonCreator
    public DeleteStudentParameters(@JsonProperty("studentId") Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeleteStudentParameters.class.getSimpleName() + "[", "]")
                .add(String.format("studentId=%s", studentId))
                .toString();
    }
}
