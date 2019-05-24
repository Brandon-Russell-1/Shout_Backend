package app.bwizlabs.dayshout.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired

import app.bwizlabs.dayshout.repo.CustomerRepository
import app.bwizlabs.dayshout.model.Customer

@RestController
class WebController {

    @Autowired
    lateinit var repository: CustomerRepository

    @RequestMapping("/save")
    fun save(): String {
        repository.save(Customer("Jack", "Smith"))
        repository.save(Customer("Adam", "Johnson"))
        repository.save(Customer("Kim", "Smith"))
        repository.save(Customer("David", "Williams"))
        repository.save(Customer("Peter", "Davis"))

        return "Done"
    }

    @RequestMapping("/findall")
    fun findAll() = repository.findAll()

    @RequestMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long)
            = repository.findById(id)

    @RequestMapping("findbylastname/{lastName}")
    fun findByLastName(@PathVariable lastName: String)
            = repository.findByLastName(lastName)

}