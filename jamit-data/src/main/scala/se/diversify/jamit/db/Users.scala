package se.diversify.jamit.db

import org.scalaquery.ql.TypeMapper._
import se.diversify.jamit.domain.Role._
import org.scalaquery.ql.extended.ExtendedTable


/**Defines mapping against the users database table */
object Users extends ExtendedTable[(Int, String, String, String, String, String)]("users") with Setup {

  def id = column[Int]("id", O PrimaryKey, O AutoInc)

  def name = column[String]("name")

  def email = column[String]("email")

  def phone = column[String]("phone")

  def role = column[String]("role")

  def password = column[String]("password")

  def * = id ~ name ~ email ~ phone ~ role ~ password

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession
  import se.diversify.jamit.util.EncryptionUtils._

  def populateTable {
    Users.insertAll(
      (0, "Kalle Karlsson", "kalle@karlsson.se", "08-123456", LocalOwner.toString, encrypt("123")),
      (0, "Lasse Larsson", "lasse@larsson.se", "08-654321", Musician.toString, encrypt("456")),
      (0, "David Davidsson", "david@davidsson.se", "08-132435", Fan.toString, encrypt("789"))
    )
  }
}


