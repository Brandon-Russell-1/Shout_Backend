package app.bwizlabs.dayshout.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import app.bwizlabs.dayshout.model.Shout
import org.springframework.data.repository.PagingAndSortingRepository


@Repository
interface ShoutRepository : PagingAndSortingRepository<Shout, Long>