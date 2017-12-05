/**********************************************************************************
* Source Code for CryptDigest
**********************************************************************************
/*
 * objCrypt.java
 *
 * Created on January 21, 2006, 5:18 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package crypt;
import java.security.MessageDigest;
import java.io.*;
/**
 *
 * @author Nick
 */
public class CryptDigest {
 /** Creates a new instance of object computDigest */
 public CryptDigest() {
 }
 String computeDigest(String inFile, String algorithm){
 File datafile = new File(inFile);
 String digest = new String();
 try {
 MessageDigest md = MessageDigest.getInstance(algorithm);
 FileInputStream fis = new FileInputStream(datafile);
 byte[] dataBytes = new byte[1024];
 int nread = fis.read(dataBytes);
 while (nread > 0) {
 md.update(dataBytes, 0, nread);
 nread = fis.read(dataBytes);
 }
 byte[] mdbytes = md.digest();
 System.out.println("Digest: " + mdbytes);
 //digest = new String(Util.byteArray2Hex(mdbytes));
 digest = new String(mdbytes);
 }catch (Exception e){
 System.out.println(e);
 }
 return digest;
 }
 }
