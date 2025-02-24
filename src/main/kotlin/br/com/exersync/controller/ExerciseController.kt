package br.com.exersync.controller

import br.com.exersync.services.ExerciseService
import br.com.exersync.domain.entities.ExerciseEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exercises")
class ExerciseController(private val exerciseService: ExerciseService) {
    @GetMapping("/{id}")
    fun getExerciseById(@PathVariable("id") id: Long): ResponseEntity<ExerciseEntity> {
        val exercise = exerciseService.getExerciseById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(exercise)
    }

    @PostMapping
    fun createExercise(@RequestBody exercise: ExerciseEntity) {

    }
}