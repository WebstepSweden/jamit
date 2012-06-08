package se.diversify.jamit.util

import org.jasypt.util.password.BasicPasswordEncryptor
import se.diversify.jamit.repository.UserDao

/**Allow Java interoperability */
class EncryptionUtils

/**Encryption utils */
object EncryptionUtils {
  private val passwordEncryptor = new BasicPasswordEncryptor

  /**
   * Encrypt the given password
   * @param password the password to encrypt
   * @return the encrpted password
   */
  def encrypt(password: String) = passwordEncryptor.encryptPassword(password)

  /**
   * Check against the database to see if the user's given password matches the saved one
   * @param email the user's email address
   * @param givenPassword the given user's password
   * @return true if user's password matches saved one, or false otherwise
   */
  def isPasswordOk(email: String, givenPassword: String): Boolean = {
    val user = UserDao.defaultDao.getByEmail(email)
    val encryptedPassword = encrypt(givenPassword)
    user.password == givenPassword
  }
}
