package app.bwizlabs.dayshout.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import app.bwizlabs.dayshout.model.Customer


@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {

    fun findByLastName(lastName: String): Iterable<Customer>
}