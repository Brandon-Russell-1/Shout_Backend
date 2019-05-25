package app.bwizlabs.dayshout.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import app.bwizlabs.dayshout.model.Shout


@Repository
interface ShoutRepository : CrudRepository<Shout, Long> {


    fun findByShoutEntry(shoutEntry: String): Iterable<Shout>

}