package br.com.exersync.services

import br.com.exersync.domain.repositories.WorkoutExerciseRepository
import org.springframework.stereotype.Service

@Service
class WorkoutExerciseService(private val workoutExerciseRepository: WorkoutExerciseRepository) {
}