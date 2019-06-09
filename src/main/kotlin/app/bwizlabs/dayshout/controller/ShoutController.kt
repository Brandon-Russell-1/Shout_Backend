package app.bwizlabs.dayshout.controller
import org.springframework.beans.factory.annotation.Autowired
import app.bwizlabs.dayshout.repo.ShoutRepository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile


@CrossOrigin
@RestController
class ShoutController {

    @Autowired
    lateinit var repository: ShoutRepository


    @RequestMapping("/save")
    fun save(): String {
        repository.save(Shout( shoutIp = "test", shoutEntry = "test", shoutLat = 0.0, shoutLong = 0.0))

        return "Done"
    }

/*    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addShout(@RequestBody shout: Shout){
        repository.save(shout)

    }*/

    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addShout(@RequestPart("shoutImage") multiPartFile : MultipartFile,
                 @RequestPart("shoutIp") shoutIp : String,
                 @RequestPart("shoutEntry") shoutEntry : String,
                 @RequestPart("shoutLat") shoutLat : Double,
                 @RequestPart("shoutLong") shoutLong : Double){
        repository.save(Shout(shoutImage = multiPartFile.bytes, mime = multiPartFile.contentType.toString(), shoutIp = shoutIp, shoutEntry = shoutEntry, shoutLat = shoutLat, shoutLong = shoutLong))


        //@RequestPart("image") multiPartFile : MultipartFile
    }


    @RequestMapping("/findall")
    fun findAll() = repository.findAll(Sort(Sort.Direction.DESC, "shoutDate"))


    @RequestMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long)
            = repository.findById(id)

   @RequestMapping("/find/{userLat}/{userLong}/{zoom}")
    fun findUserLocationShouts(@Param("userLat") userLat: Double, @Param("userLong") userLong: Double, @Param("zoom") zoom: Integer): Iterable<Shout> {

       return repository.findUserLocationShouts(userLat, userLong, zoom)


    }


    //This is a Kotlin extension function that turns a MultipartFile into a PersistedImage
  //  fun MultipartFile.toPersistedImage() = Shout(shoutImage = this.bytes, mime = this.contentType)



}