package br.com.exersync.services

import br.com.exersync.domain.entities.ExerciseEntity
import br.com.exersync.domain.repositories.ExerciseRepository
import org.springframework.stereotype.Service

@Service
class ExerciseService(private val exerciseRepository: ExerciseRepository) {
    fun getExerciseById(id: Long): ExerciseEntity? {
        return exerciseRepository.findById(id.toInt()).orElse(null)
    }

    fun createExercise(exercise: ExerciseEntity): ExerciseEntity {
        return exerciseRepository.save(exercise)
    }
}