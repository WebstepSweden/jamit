package se.diversify.jamit.domain

import com.codahale.jerkson.AST.{JValue, JString}


class Role(val name: String) {
  override def toString: String = name
}

object Role {

  case object LocalOwner extends Role("Local owner")

  case object Musician extends Role("Musician")

  case object Fan extends Role("Fan")

  implicit def role2String(role: Role): String = role.name
}
