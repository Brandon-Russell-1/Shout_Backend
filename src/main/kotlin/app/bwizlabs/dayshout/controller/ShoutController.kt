package app.bwizlabs.dayshout.controller
import org.springframework.beans.factory.annotation.Autowired
import app.bwizlabs.dayshout.repo.ShoutRepository
import app.bwizlabs.dayshout.model.Shout
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.awt.Graphics2D
import java.awt.Image
import java.io.ByteArrayOutputStream


@CrossOrigin(value = "*", exposedHeaders = ["Content-Disposition"])
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


    @RequestMapping("/add", method = [RequestMethod.POST])
    fun addShout(@RequestParam("shoutImage") multiPartFile : MultipartFile,
                 @RequestParam("shoutIp") shoutIp : String,
                 @RequestParam("shoutEntry") shoutEntry : String,
                 @RequestParam("shoutLat") shoutLat : Double,
                 @RequestParam("shoutLong") shoutLong : Double
){

      //  println(multiPartFile.contentType.toString().substring(multiPartFile.contentType.toString().indexOf('/')+1))

        var imageType = "png"
        if(multiPartFile.contentType.toString().substring(multiPartFile.contentType.toString().indexOf('/')+1) == "jpeg" ||
                multiPartFile.contentType.toString().substring(multiPartFile.contentType.toString().indexOf('/')+1) == "jpg")
            imageType = "png"
        else if(multiPartFile.contentType.toString().substring(multiPartFile.contentType.toString().indexOf('/')+1) == "png")
            imageType = "png"
        else if(multiPartFile.contentType.toString().substring(multiPartFile.contentType.toString().indexOf('/')+1) == "gif")
            imageType = "gif"


        // convert byte array back to BufferedImage
        val `in` = ByteArrayInputStream(multiPartFile.bytes)
        val bImageFromConvert = ImageIO.read(`in`)

        var imageWidth = bImageFromConvert.width
        var imageHeight = bImageFromConvert.height
        var imageSizeFactor = 2

        //resize for table thumbnail
        val resized = resize(bImageFromConvert, 100, 100)

        val baos = ByteArrayOutputStream()
        ImageIO.write(resized, imageType, baos)
        baos.flush()
        val imageInByte = baos.toByteArray()

        //resize for main image file
/*        var imageInByteLarge = baos.toByteArray()

        if (imageType != "gif"){

            if(imageWidth > 800 || imageHeight > 800) {
                imageSizeFactor = 2
            }

            val resizedLarge = resize(bImageFromConvert, imageHeight/imageSizeFactor, imageWidth/imageSizeFactor)
            //convert BufferedImage to byte array


            val baos2 = ByteArrayOutputStream()
            ImageIO.write(resizedLarge, imageType, baos2)
            baos2.flush()
            imageInByteLarge = baos2.toByteArray()


        }else{
            imageInByteLarge = multiPartFile.bytes
        }*/


        repository.save(Shout(shoutImage = multiPartFile.bytes, shoutSmallImage = imageInByte,mime = multiPartFile.contentType, shoutIp = shoutIp, shoutEntry = shoutEntry, shoutLat = shoutLat, shoutLong = shoutLong))

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


    private fun resize(img: BufferedImage, height: Int, width: Int): BufferedImage {
        val tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        val resized = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g2d = resized.createGraphics()
        g2d.drawImage(tmp, 0, 0, null)
        g2d.dispose()
        return resized
    }



}