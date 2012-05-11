package se.diversify.jamit.db

import org.scalaquery.ql.TypeMapper._
import se.diversify.jamit.domain.Role._
import org.scalaquery.ql.extended.ExtendedTable

object Users extends ExtendedTable[(Int, String, String, String)]("users") {

  def id = column[Int]("id", O PrimaryKey, O AutoInc)

  def name = column[String]("name")

  def email = column[String]("email")

  def role = column[String]("role")

  def * = id ~ name ~ email ~ role

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession

  def createDefaultData {
    DB.database withSession {
      Users.insertAll(
        (0, "Kalle Karlsson", "kalle@karlsson.se", LocalOwner),
        (0, "Lasse Larsson", "lasse@larsson.se", Musician),
        (0, "David Davidsson", "david@davidsson.se", Fan)
      )
    }
  }
}


