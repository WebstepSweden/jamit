package se.diversify.jamit.util

import com.codahale.jerkson.AST.JString

class JsonUtils

object JsonUtils {
  import com.codahale.jerkson.Json._

  def toJson(thing: AnyRef): String = generate(thing)

  def ok: String = generate(("status", "ok"))
}
