package br.com.exersync.domain.repositories

import br.com.exersync.domain.entities.ExerciseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<ExerciseEntity, Int>