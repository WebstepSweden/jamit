package se.diversify.jamit.db

import org.scalaquery.ql.extended.ExtendedTable

/**Defines mapping against the venues database table */
object Venues extends ExtendedTable[(Int, String, String, String, String, Boolean, Int, String)]("venues") with Setup {

  def id = column[Int]("id", O PrimaryKey, O AutoInc)

  def name = column[String]("name")

  def address = column[String]("address")

  def postalCode = column[String]("postalCode")

  def city = column[String]("city")

  def public = column[Boolean]("public")

  def maximumNoOfMusicians = column[Int]("maximumNoOfMusicians")

  def contact = column[String]("contct")

  def * = id ~ name ~ address ~ postalCode ~ city ~ public ~ maximumNoOfMusicians ~ contact

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession

  def populateTable {
    Venues.insertAll(
      (0, "Hemma hos Mats", "Matsgatan 43", "123 45", "Stockholm", false, 5, "mats@mats.com"),
      (0, "Hemma hos Uzi", "Uzigatan 43", "234 56", "Stockholm", false, 6, "uzi@uzi.com"),
      (0, "Hemma hos Kristoffer", "Kristoffergatan 43", "345 67", "Stockholm", false, 3, "kristoffer@kristoffer.com")
    )
  }
}
