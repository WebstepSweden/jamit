package se.diversify.jamit.util

/**Allow Java interoperability */
class JsonUtils

/**Json utilities */
object JsonUtils {

  import com.codahale.jerkson.Json._

  /**Return a Json representation of an object
   * @param thing object the object to represent as a Json
   * @return a Json representation of an object
   */
  def toJson(thing: AnyRef): String = generate(thing)

  /**Create an ok message in Json form */
  def ok: String = generate(Map("status" -> "ok"))

  /**Create a not ok message in Json form */
  def notOk(message: String): String = generate(Map("status" -> "not ok", "message" -> message))

  /**Create a not ok message in Json form, given an Exception */
  def notOk(ex: Exception): String = generate(Map("status" -> "not ok", "message" -> ex.getMessage, "stacktrace" -> ex.getStackTrace))
}
