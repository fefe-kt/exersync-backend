package br.com.exersync.services

import br.com.exersync.domain.repositories.WorkoutListRepository
import org.springframework.stereotype.Service

@Service
class WorkoutListService(private val workoutListRepository: WorkoutListRepository) {
}