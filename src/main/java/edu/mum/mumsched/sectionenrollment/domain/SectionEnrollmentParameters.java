package edu.mum.mumsched.sectionenrollment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.mum.mumsched.students.model.DeleteStudentParameters;

import java.util.StringJoiner;

public class SectionEnrollmentParameters {
    private final Long sectionId;

    @JsonCreator
    public SectionEnrollmentParameters(@JsonProperty("sectionId") Long studentId) {
        this.sectionId = studentId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeleteStudentParameters.class.getSimpleName() + "[", "]")
                .add(String.format("sectionId=%s", sectionId))
                .toString();
    }
}
