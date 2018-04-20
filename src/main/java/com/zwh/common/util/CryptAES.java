package com.zwh.common.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * ClassName: CryptAES(AES加密类 可与PHP对应)
 * @Version 1.0
 * @Author: airufei
 * date: 2016年10月31日 上午10:02:19
 */
public class CryptAES { 
//	public static ResourceBundle resourceBundleb = ResourceBundle.getBundle("jeesite");
    private static final String AESTYPE ="AES/ECB/PKCS5Padding"; 
    private static Logger logger = LoggerFactory.getLogger(CryptAES.class);
    /**
     * AES_Encrypt:(加密)
     * @Author airufei
     * @param keyStr
     * @param plainText
     * @return
     */
    public static String AES_Encrypt(String keyStr, String plainText) { 

        byte[] encrypt = null; 

        try{ 

            Key key = generateKey(keyStr); 

            Cipher cipher = Cipher.getInstance(AESTYPE); 

            cipher.init(Cipher.ENCRYPT_MODE, key); 

            encrypt = cipher.doFinal(plainText.getBytes());     

        }catch(Exception e){ 
        	logger.error("AES_Encrypt:(加密)失败======>:"+e.getMessage());
        }

        return new String(Base64.encodeBase64(encrypt)); 

    } 

 

    /**
     * AES_Decrypt:(解密)
     * @Author airufei
     * @param keyStr
     * @param encryptData
     * @return
     */
    public static String AES_Decrypt(String keyStr, String encryptData) {
        byte[] decrypt = null; 
        try{ 

            Key key = generateKey(keyStr); 

            Cipher cipher = Cipher.getInstance(AESTYPE); 

            cipher.init(Cipher.DECRYPT_MODE, key); 

            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData)); 

        }catch(Exception e){ 
        	logger.error(" AES_Decrypt:(解密)失败======>:"+e.getMessage());
        } 

        return new String(decrypt).trim(); 

    } 

   
    
    
    /**
     * AES_CRM_Encrypt:(加密)
     * @Author airufei
     * @param keyStr
     * @param plainText
     * @return
     */
    public static String AES_CRM_Encrypt(String plainText,String aeskey) { 
    	 String encryptData=AES_Encrypt(aeskey,plainText);
    	 return encryptData;
    } 
    /**
     * AES_CRM_Encrypt:(加密)
     * @Author airufei
     * @param keyStr
     * @param plainText
     * @return
     */
//    public static String AES_CRM_Encrypt(String plainText) { 
////    	 String keyStr=resourceBundleb.getString("crm.aeskey");
//    	 String encryptData=AES_Encrypt(aeskey,plainText);
//    	 return encryptData;
//    } 
    
    /**
     * AES_Decrypt:(解密)
     * @Author airufei
     * @param keyStr
     * @param encryptData
     * @return
     */
    public static String AES_CRM_Decrypt(String encryptData,String aeskey) {
    	String  Str= AES_Decrypt(aeskey,encryptData);
        return Str; 

    } 
    /**
     * AES_Decrypt:(解密)
     * @Author airufei
     * @param keyStr
     * @param encryptData
     * @return
     */
//    public static String AES_CRM_Decrypt(String encryptData) {
////    	 String keyStr=resourceBundleb.getString("crm.aeskey");
//    	String  Str= AES_Decrypt(aeskey,encryptData);
//        return Str; 
//
//    } 
    private static Key generateKey(String key)throws Exception{ 
        try{    
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES"); 
            return keySpec; 
        }catch(Exception e){ 
        	logger.error(" generateKey:(获取key)失败======>:"+e.getMessage());
            throw e; 

        } 

    } 

    /**
     * @秘钥加密
     * @param pkey  time.pkey  时间戳+明文秘钥
     * @return 
     * @throws NoSuchAlgorithmException 
     */
    @SuppressWarnings({ "static-access" })
	public static String getToken(String key, String pkey) throws Exception {
        CryptAES ca = new CryptAES();
        //AES加密
        String encText = ca.AES_Encrypt(key, pkey);
        return encText;  
    }
    /**
     * @秘钥加密
     * @param pkey  time.pkey  时间戳+明文秘钥
     * @return 
     * @throws NoSuchAlgorithmException 
     */
//    @SuppressWarnings({ "static-access" })
//	public static String getToken(String pkey) throws Exception {
//        CryptAES ca = new CryptAES();
////        String key = resourceBundleb.getString("android.puhui_key");
//        //AES加密
//        String encText = ca.AES_Encrypt(aeskey, pkey);
//        return encText;  
//    }

}
