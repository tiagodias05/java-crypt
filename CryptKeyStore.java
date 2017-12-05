/**********************************************************************************
* Source Code for CryptKeyStore
**********************************************************************************
/*
 * crypto.java
 *
 * Created on April 20, 2006, 9:03 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
 
 /* teste2 */
package crypt;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.util.Enumeration;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
/*
 *
 * @author Nick
 */
public class CryptKeyStore {
 /** Creates a new instance of kStore */
 public CryptKeyStore() {
 }
 /** This method is used to create a new kesytore. */
 public int createKeystore(String keyFilePath, String password){
 KeyStore ks = null;
 int result;
 /*Create concrete KeyStore instance and then
 *Load needs to be done before accessing the keystore. In
 *this case we are creating it empty by passing in null.
 *We then store the file and close it*/
 try {
 ks = KeyStore.getInstance("JCEKS");
 ks.load(null, password.toCharArray());
 java.io.FileOutputStream fos = new java.io.FileOutputStream(keyFilePath);
 ks.store(fos, password.toCharArray());
 fos.close();
 result = 1;
 //success
 }catch (Exception e){
 System.out.println(e);
 result = 0; //failure
 }
 return result;
 }
 /** This method is used to add a key to a current keystore */
 public int addKey(String keyFilePath, String password, SecretKey sKey, String
alias){
 KeyStore ks = null; //creates the keystore
 int result;
 PasswordProtection pass = new PasswordProtection(password.toCharArray());
//creates the password protection for the keystore
 /*Initialize (load) the keystore*/
 try {
 ks = this.initKeystore(keyFilePath, password);
 KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(sKey);
 ks.setEntry(alias, skEntry, pass);
 java.io.FileOutputStream fos = new java.io.FileOutputStream(keyFilePath);
 ks.store(fos, password.toCharArray());
 fos.close();
 result = 1; //success
 }catch (Exception e){
 result= 0; //failure
 System.out.println(e);
 }
 return result;
 }
 /** This method is used to add a key to a current keystore */
 public int deleteKey(String keyFilePath, String password, String alias){KeyStore ks = null; //creates the keystore
 int result;
 PasswordProtection pass = new PasswordProtection(password.toCharArray());

 //creates the password protection for the keystore
 /*Initialize (load) the keystore*/
 try {
 ks = this.initKeystore(keyFilePath, password);
 ks.deleteEntry(alias);
 java.io.FileOutputStream fos = new java.io.FileOutputStream(keyFilePath);
ks.store(fos, password.toCharArray());
 fos.close();
 result = 1; //success
 }catch (Exception e){
 result= 0; //failure
 System.out.println(e);
 }
 return result;
 }
 /** This method is used to retrieve a key from a current keystore */
 public SecretKey getKey(String keyFilePath, String password, String alias){System.out.println("getKey");
 KeyStore ks = null; //creates the keystore
 SecretKey sKey = null; //creates the secret key that will be returned
 PasswordProtection pass = new PasswordProtection(password.toCharArray());
//creates the password protection for the keystore
 /*Initialize (load) the keystore*/
 try {
 ks = this.initKeystore(keyFilePath, password);
 KeyStore.SecretKeyEntry skEntry = (KeyStore.SecretKeyEntry)ks.getEntry(alias, pass);
 sKey = skEntry.getSecretKey();
 }catch (Exception e){
 System.out.println(e);
 }
 System.out.println(sKey.getEncoded());
 return sKey;
 }
 /** This method is a private method that is used to initialize (load) the keystore
*/
 private KeyStore initKeystore(String keyFilePath, String password){
 System.out.println("initKeystore");
 KeyStore ks = null;
 /*Loads the keystore with the given pathname and password*/
 try {
 ks = KeyStore.getInstance("JCEKS");
 java.io.FileInputStream fis = new java.io.FileInputStream(keyFilePath);
 ks.load(fis, password.toCharArray());
 fis.close();
 }catch (Exception e){
 System.out.println(e);
 }
 return ks;
 }
 public Enumeration getAliases(String keyFilePath, String password){
 KeyStore ks = null; //creates the keystore
 Enumeration aliases = null;
 PasswordProtection pass = new PasswordProtection(password.toCharArray());
//creates the password protection for the keystore
/*Initialize (load) the keystore*/
 try {
 ks = this.initKeystore(keyFilePath, password);
 aliases = ks.aliases();
 }catch (Exception e){
 System.out.println(e);
 JOptionPane.showMessageDialog(null,"Failed to retrieve Aliases","KeyStore",JOptionPane.WARNING_MESSAGE);
 }
 return aliases;
 }
 }
