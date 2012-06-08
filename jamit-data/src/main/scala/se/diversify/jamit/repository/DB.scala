package se.diversify.jamit.repository

import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.session._
import org.scalaquery.ql.extended.ExtendedTable
import se.diversify.jamit.db.Users

/**Allow Java interoperability */
class DB

/**Define common database operations */
object DB {

  /**Define a database connection object */
  private[repository] val database = Database.forURL(
    "jdbc:mysql://localhost:3306/jamit",
    driver = "com.mysql.jdbc.Driver",
    user = "jamit",
    password = "Zzwmr5TBSK8Gac"
  )

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession

  val tables = List(Users)
  DB.database withSession {
    for (table <- tables) {
      table.ddl.drop
      table.ddl.create
      table.populateTable
    }
  }
}