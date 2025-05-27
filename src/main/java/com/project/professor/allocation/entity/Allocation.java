package com.project.professor.allocation.entity;

import java.sql.Time;
import java.time.DayOfWeek;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Allocation {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DayOfWeek day;
	
	@Schema(example = "08:00:00", type = "string")
	@Column(name = "startHour", nullable = false)
	private Time start;
	
	@Schema(example = "17:00:00", type = "string")
	@Column(name = "endHour", nullable = false)
	private Time end;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;
	public void setProfessorId(Long professorId) {
		this.professor = new Professor();
		this.professor.setId(professorId);
	}
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	public void setCourseId(Long courseId) {
		this.course = new Course();
		this.course.setId(courseId);
	}
}
