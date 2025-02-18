package br.com.exersync.domain.repositories

import br.com.exersync.domain.entities.WorkoutExerciseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WorkoutExerciseRepository : JpaRepository<WorkoutExerciseEntity, Int>