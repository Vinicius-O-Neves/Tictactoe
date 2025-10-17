import org.slf4j.LoggerFactory

object LoggerExtensions {

    private val logger = LoggerFactory.getLogger("TictacToeApp")

    fun d(message: String) = logger.debug(message)
    fun i(message: String) = logger.info(message)
    fun w(message: String) = logger.warn(message)
    fun e(message: String, throwable: Throwable? = null) = logger.error(message, throwable)

}