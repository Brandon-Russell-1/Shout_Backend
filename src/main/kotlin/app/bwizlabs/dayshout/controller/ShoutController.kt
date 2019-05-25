package app.bwizlabs.dayshout.controller
import org.springframework.beans.factory.annotation.Autowired
import app.bwizlabs.dayshout.repo.ShoutRepository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RequestMapping("/")
@RestController
class ShoutController {

    @Autowired
    lateinit var repository: ShoutRepository


    @RequestMapping("/save")
    fun save(): String {
        repository.save(Shout( shoutIp = "test", shoutEntry = "test", shoutLat = 0.0, shoutLong = 0.0))

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


}