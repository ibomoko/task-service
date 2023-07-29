
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.util.*


@Suppress("unused")
class ErrorResponse (val message: String?, val error: Int?, val status: HttpStatus?) {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private val timestamp: Date = Date()

}
