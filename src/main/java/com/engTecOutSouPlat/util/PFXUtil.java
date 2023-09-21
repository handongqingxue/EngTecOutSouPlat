package com.engTecOutSouPlat.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

//https://codeleading.com/article/12883687593/
//https://www.cnblogs.com/niway/p/17656048.html
//https://www.freesion.com/article/1566758914/
public class PFXUtil {
	
    public static final String PKCS12 = "PKCS12";
    public static final String JKS = "JKS";
    public static final String PFX_KEYSTORE_FILE = "d://certificate.pfx";
    public static final String KEYSTORE_PASSWORD = "123456";
    public static final String JKS_KEYSTORE_FILE = "d://certificate.jks";
 
    public static void coverToPfx() {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream(JKS_KEYSTORE_FILE);
            char[] nPassword = null;
 
            if ((KEYSTORE_PASSWORD == null)
                    || KEYSTORE_PASSWORD.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }
 
            inputKeyStore.load(fis, nPassword);
            fis.close();
 
            KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");
 
            outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
 
            Enumeration enums = inputKeyStore.aliases();
 
            while (enums.hasMoreElements()) { //   we   are   readin   just   one   certificate.
 
                String keyAlias = (String) enums.nextElement();
 
                System.out.println("alias=[" + keyAlias + "]");
 
                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
                    Certificate[] certChain = inputKeyStore
                            .getCertificateChain(keyAlias);
 
                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD
                            .toCharArray(), certChain);
                }
            }
 
            FileOutputStream out = new FileOutputStream(PFX_KEYSTORE_FILE);
 
            outputKeyStore.store(out, nPassword);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        coverToPfx();
    }
}
