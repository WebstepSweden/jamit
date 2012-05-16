package se.diversify.jamit.domain

import com.codahale.jerkson.AST.{JValue, JString}

/**A user's role
 * @param name the name of the role
 */
class Role(val name: String) {
  override def toString: String = name
}

/**Common role definitions */
object Role {

  /**A local owner which can host jams */
  case object LocalOwner extends Role("Local owner")

  /**A musician which can play in jams */
  case object Musician extends Role("Musician")

  /**A music fan who can attend and listen in jams */
  case object Fan extends Role("Fan")

  /**Convert a role into a String */
  implicit def role2String(role: Role): String = role.name
}
