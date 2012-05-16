package se.diversify.jamit.db

import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.session._

/**Allow Java interoperability */
class DB

/**Define common database operations */
object DB {

  /**Define a database connection object */
  private[db] val database = Database.forURL(
    "jdbc:mysql://localhost:3306/jamit",
    driver = "com.mysql.jdbc.Driver",
    user = "jamit",
    password = "Zzwmr5TBSK8Gac"
  )
  //createTables
  //createDefaultData

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._

  private def createTables {
    database withSession {
      Users.ddl create
    }
  }

  private def createDefaultData {
    Users.createDefaultData
  }
}