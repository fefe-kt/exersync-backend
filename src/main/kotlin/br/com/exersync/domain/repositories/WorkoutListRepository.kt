package br.com.exersync.domain.repositories

import br.com.exersync.domain.entities.WorkoutListEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WorkoutListRepository : JpaRepository<WorkoutListEntity, Int>