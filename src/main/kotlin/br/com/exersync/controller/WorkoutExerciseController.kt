package br.com.exersync.controller

import br.com.exersync.services.WorkoutExerciseService
import org.springframework.web.bind.annotation.RestController

@RestController
class WorkoutExerciseController(private val workoutExerciseService: WorkoutExerciseService) {

}