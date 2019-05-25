package app.bwizlabs.dayshout.controller

import org.springframework.beans.factory.annotation.Autowired

import app.bwizlabs.dayshout.repo.ShoutRepository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.web.bind.annotation.*
import java.sql.Date
import java.time.LocalDate


@RestController
class ShoutController {

    @Autowired
    lateinit var repository: ShoutRepository

    @RequestMapping("/save")
    fun save(): String {
//        repository.save(Shout(shoutDate = LocalDate(5/24/2019), shoutEntry = "What up bro!!!", shoutLat = 32.5, shoutLong = 23.4))

        return "Done"
    }

    @RequestMapping("/findall")
    fun findAll() = repository.findAll()

    @RequestMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long)
            = repository.findById(id)

    @RequestMapping("findByShout/{shoutEntry}")
    fun findByShout(@PathVariable shoutEntry: String)
            = repository.findByShoutEntry(shoutEntry)

 /*   @PostMapping
    @RequestMapping(value = "/newShout")
    fun createUser(@RequestBody newShout: Shout): Shout {
        val shout = Shout(
                shoutDate = LocalDate.now(),
                shoutEntry = newShout.shoutEntry,
                shoutLat = newShout.shoutLat,
                shoutLong = newShout.shoutLong
        )
        return shout
    }*/
}