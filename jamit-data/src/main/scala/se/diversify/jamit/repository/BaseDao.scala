package se.diversify.jamit.repository

import org.bson.types.ObjectId

/**Base crud definition of the dao classes
 * @tparam T the type of the item this dao handles
 */
trait BaseDao[T] {

  /**Get an item from the database given an ObjectId
   * @param id the item's ObjectId
   * @return the item instance
   */
  def get(id: ObjectId): T

  /**Get an item from the database given an id
   * @param id the item's id
   * @return the item instance
   */
  def get(id: Int): T

  /**Update item information
   * @param item the item to _update
   * @return the updated item
   */
  def update(item: T): T

  /**Add a new item in the database
   * @param item the item to add
   * @return the newly added item
   */
  def add(item: T): T

  /**Remove an item from the database given its ObjectId
   * @param id the item id
   */
  def delete(id: ObjectId)

  /**Remove an item from the database given its id
   * @param id the item id
   */
  def delete(id: Int)

  /**Retrieve all items in the database
   * @return all the items in the database
   */
  def getAll(): List[T]
}
