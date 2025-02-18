package br.com.exersync.controller

import br.com.exersync.services.WorkoutListService
import org.springframework.web.bind.annotation.RestController

@RestController
class WorkoutListController(private val workoutListService: WorkoutListService) {

}