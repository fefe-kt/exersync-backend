package br.com.exersync.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne


@Entity
@Table(name ="workout_exercises")
data class WorkoutExerciseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "workout_list_id", nullable = false)
    val workoutList: WorkoutListEntity,

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    val exercise: ExerciseEntity,

    val repetitions: Int,
    val sets: Int,
    val restDurationInSeconds: Int,
    val duration: Int?,
)
