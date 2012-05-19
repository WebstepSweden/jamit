package se.diversify.jamit.db

import org.scalaquery.ql.basic.AbstractBasicTable

/**Contains methods that should be implemented by ExtendedTables to allow setting up the table */
trait Setup {

  /**Populate the table */
  def populateTable

}
