package com.zwh.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DESCryptUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DESCryptUtil.class);
	
    private final static String DES_KEY = "天jfr@#8$8CAB4D1BB鹏@#$%^&*()_+(*最&$5537*帅";
	
	private final static String DES3_KEY = "r!s9?j&9a@#8$0%dfkl;4*da";
	
	private static final String Algorithm = "DESede";
	
	private static String DES_CREDIT_KEY =null;

	/** 
     * 数据加密，算法（DES） 
     * 
     * @param data 
     *            要进行加密的数据 
     * @return 加密后的数据 
     */  
    public static String encryptBasedDes(String data) {  
        String encryptedData = null;  
        try { 
            // DES算法要求有一个可信任的随机数源  
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
            SecretKey key =  keyFactory.generateSecret(deskey); 
            // 加密对象  
            Cipher cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
            // 加密，并把字节数组编码成字符串  
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {  
        	logger.error("加密错误，错误信息：", e);  
            throw new RuntimeException("加密错误，错误信息：", e);  
        }  
        return encryptedData;  
    } 
    public static void main(String[] args) {
    	String str = decryptBasedDes("IXA2aqsP/K4zjJa8527LDlYfm9pg/tihrGMIZEqCQsnf//81kLfFSeN/PNATYbSB7LXq9A88stYiIg/g+TiLmrJsFQO/RXPhw0hTpQRFwQo6FzbDwQuKXEIp05C+HUVkc6WdD8yyi2Lxan5xtC5EYdcrqXsZRE4XIhpifvSDXd304ZecrWbz0Z5XrWxl66dLjYpw3OJJqm785+jR70mkij0RzGS4Xb41P+1Gu15LMquUnQCSgBTsOP9EFfFOlU1rIWBl0tMXrXiUT1yS89tPiGyIDVwy9bMWRC5SBitZekXT1JFaq+p9r+QMhS26jzvnGl9pwXZuwupelh5ODTURADChpQJe8g9t6VtX4viw4F+DsK1nUv/nwlAbqwyn4bn1zeQjlqHYGbyVtnpnhi9g4rdDSoDucHmKNyvonmJwLBcZa1V0YbuJg+8Fy6ooX/h8DxQ8WBSTrnKTNSUNmDpCySc0rSJ5V3FdonJCkl5H7PVANtKZzeu9Xk4gOJCGISiJsiGiNYqu9RLbeVUzBULESOHclb1kQXYeRtKsEuGJLQgulGE7je8ADgf7jAwz8z4XY50xIU3bUmJiek2r7KOrGaM/+X3qSorX+jWBOmI3s6GjHj2gD9Lp4m7ijFuCSlyrQDbSmc3rvV5YCcgMs1N/JQ52pw9hXDVzARFt5yTW3Cj9h2uoUarqUqX8VhwTPvanxjcabW6mCGfBOugDkFQTU2oju5NZyosKYnpNq+yjqxl86AQ0ceZHg0XRO+2IqS3Z1MdoJCwNvlAjR8p5cphr+Ztye1+73JHOUqc4hEUDZaY61fOUXENONtKen4JPp3YAjj4b7Eg/d3Byk3HiKoOqTJzNgH3KKIoSPKJi/AWqYdc6ni7Cp885lS77BozhRj+gQGP87xzFrA7ONVOW/jf6Ywd4r8/QvGs45keigULZnu7y9dWMYQa+WsqA1/uMeNCptKDTOLQhhgALx5PmiMtfBqltaKq8MkmJTxSH57JXJ5qgoVwl/Cmpg9Bx10YQTmnugY36oXDyc2SLcKEHgGROefsy8uN28bgEj0C7BZAJ0AwCskU+alBkXDzPQ17VHoZ3liF5fa5M/fMYPODEYfT7SASFrNEcYM0XZ2+OK2CLeH21E5WP4nfveIOEvcBKnTQe89KTTKYPZ2oCqtZDkJcXNw==");
    	System.out.println(str);
	}
	/** 
     * 数据解密，算法（DES） 
     * 
     * @param cryptData 
     *            加密数据 
     * @return 解密后的数据 
     */  
    public static String decryptBasedDes(String cryptData) {
        String decryptedData = null;
        cryptData = cryptData.trim();
        try {
            byte[] decryptStr = new sun.misc.BASE64Decoder().decodeBuffer(cryptData.replace(" ", "+"));
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 把字符串解码为字节数组，并解密
            byte[] decryResult = cipher.doFinal(decryptStr);
            decryptedData = new String(decryResult, "UTF-8");
        } catch (Exception e) {
            logger.error("解密错误，错误信息：", e);
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }
    
    public static String encryptBased3Des(String data) {  
        String encryptedData = null;  
        logger.error("key length : " + DES3_KEY.getBytes().length);
        try { 
        	// 生成密钥
            SecretKey deskey = new SecretKeySpec(DES3_KEY.getBytes(), Algorithm);
            // 加密
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
         // 加密，并把字节数组编码成字符串  
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {  
        	logger.error("加密错误，错误信息：", e);   
        }  
        return encryptedData;  
    } 
    
    public static String decryptBased3Des(String cryptData) {  
        String decryptedData = null;  
        try {
        	byte[] decryptStr = new sun.misc.BASE64Decoder().decodeBuffer(cryptData);
        	// 生成密钥
            SecretKey deskey = new SecretKeySpec(DES3_KEY.getBytes(), Algorithm);
            // 解密
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            byte [] decryResult = cipher.doFinal(decryptStr);
            decryptedData = new String(decryResult);
        } catch (Exception e) {  
            logger.error("解密错误，错误信息：", e);  
        }  
        return decryptedData;  
    }
    
    /**
	 * 字符串加密——征信秘钥
	 * @author lcy
	 * @param data
	 * @return 加密字符串
	 */
	@SuppressWarnings("restriction")
	public static String encryptBased(String data) {
		String encryptedData = null;
		DES_CREDIT_KEY="jfr好@#8$am07&4*";
		try {
			// while(data.length()%8 != 0){
			// data = data + " ";
			// }
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_CREDIT_KEY.getBytes("UTF-8"));
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher
					.doFinal(data.getBytes("UTF-8")));
		} catch (Exception e) {
			logger.error("加密错误，错误信息：", e);
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}
	

    /**
     * 字符串解密——大数据
     * 
     * @author lcy
     * @param cryptData
     * @return 解密字符串
     */
    public static String getdecryptBased(String cryptData) {
        String decryptedData = null;
        try {
            byte[] decryptStr = new sun.misc.BASE64Decoder().decodeBuffer(cryptData.replace(" ", "+"));
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec("jfr好@#8$am07&4*".getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 把字符串解码为字节数组，并解密
            byte[] decryResult = cipher.doFinal(decryptStr);
            decryptedData = new String(decryResult, "UTF-8");
        } catch (Exception e) {
            logger.error("解密错误，错误信息：", e);
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }
}