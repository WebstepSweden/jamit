package se.diversify.jamit.db

import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.session._

class DB

object DB {

  private[db] val database = Database.forURL(
    "jdbc:mysql://localhost:3306/jamit",
    driver = "com.mysql.jdbc.Driver",
    user = "jamit",
    password = "Zzwmr5TBSK8Gac"
  )
  //createTables
  //createDefaultData

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._

  def createTables {
    database withSession {
      Users.ddl create
    }
  }

  def createDefaultData {
    Users.createDefaultData
  }
}