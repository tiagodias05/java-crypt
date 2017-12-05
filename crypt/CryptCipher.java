/**********************************************************************************
* Source Code for CryptCipher
**********************************************************************************
/*
 * cryptCipher.java
 *
 * Created on April 21, 2006, 9:28 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package crypt;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
/**
 *
 * @author Nick
 */
public class CryptCipher {
 /** Creates a new instance of cryptCipher */
 public CryptCipher() {
 }
 /** This method is used to encrypt a file using DES */
 public int encrypt(String inFilePath, String outFilePath, SecretKey sKey){
 System.out.println("encrypt");
 int result;
 FileInputStream inputFile;
 FileOutputStream outputFile;
 CipherOutputStream cipherOutputStream;
 Cipher myCipher;
 try {
	 myCipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //creating the concrete cipher
 myCipher.init(Cipher.ENCRYPT_MODE, sKey); //initializing the cipher for encryption
 inputFile = new FileInputStream(inFilePath); //creating input file stream
 outputFile = new FileOutputStream(outFilePath); //creating output filestream
 cipherOutputStream = new CipherOutputStream(outputFile, myCipher); //create output cipher stream
 int i = 0;
 while ((i = inputFile.read()) != -1) {
 cipherOutputStream.write(i);
 }
 cipherOutputStream.close();
 outputFile.close();
 inputFile.close();
 result = 1; //success
 }catch (Exception e){
 result = 0; //failure
 System.out.println(e);
 }
 return result;
 }
 /** This method is used to decrypt a file using DES */
 public int decrypt(String inFilePath, String outFilePath, SecretKey sKey){
 System.out.println("decrypt");
 int result;
 FileInputStream inputFile;
 FileOutputStream outputFile;
 CipherInputStream cipherInputStream;
 Cipher myCipher;
 try {
 myCipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //creating the concrete cipher
 myCipher.init(Cipher.DECRYPT_MODE, sKey); //initializing the cipher for decryption
 inputFile = new FileInputStream(inFilePath); //creating input file stream
 outputFile = new FileOutputStream(outFilePath); //creating output file stream
 cipherInputStream = new CipherInputStream(inputFile, myCipher); //create input cipher stream
 int i = 0;
 while ((i = cipherInputStream.read()) != -1) {
 outputFile.write(i);
 }

 cipherInputStream.close();
 outputFile.close();
 inputFile.close();
 result = 1; //success
 }catch (Exception e){
 result = 0; //failure
 System.out.println(e);
 }
 return result;
 }
}
