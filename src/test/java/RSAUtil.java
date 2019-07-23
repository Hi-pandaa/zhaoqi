/*
 --------------------------------------------**********--------------------------------------------
 该算法于1977年由美国麻省理工学院MIT(Massachusetts Institute of Technology)的Ronal Rivest，Adi Shamir和Len Adleman三位年轻教授提出，并以三人的姓氏Rivest，Shamir和Adlernan命名为RSA算法，是一个支持变长密钥的公共密钥算法，需要加密的文件快的长度也是可变的!
 所谓RSA加密算法，是世界上第一个非对称加密算法，也是数论的第一个实际应用。它的算法如下：
 1.找两个非常大的质数p和q（通常p和q都有155十进制位或都有512十进制位）并计算n=pq，k=(p-1)(q-1)。
 2.将明文编码成整数M，保证M不小于0但是小于n。
 3.任取一个整数e，保证e和k互质，而且e不小于0但是小于k。加密钥匙（称作公钥）是(e, n)。
 4.找到一个整数d，使得ed除以k的余数是1（只要e和n满足上面条件，d肯定存在）。解密钥匙（称作密钥）是(d, n)。
 加密过程： 加密后的编码C等于M的e次方除以n所得的余数。
 解密过程： 解密后的编码N等于C的d次方除以n所得的余数。
 只要e、d和n满足上面给定的条件。M等于N。
 --------------------------------------------**********--------------------------------------------
 */



import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class RSAUtil {
	
	/** 指定key的大小 */
	private static int KEYSIZE = 2048;
	/**
	 * 生成密钥对
	 */
	public static Map<String, String> generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.encodeBase64(publicKeyBytes),
				ConfigureEncryptAndDecrypt.CHAR_ENCODING);
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.encodeBase64(privateKeyBytes),
				ConfigureEncryptAndDecrypt.CHAR_ENCODING);

		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.encodeBase64(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public static String encrypt(String source, String publicKey) throws Exception {
		Key key = getPublicKey(publicKey);
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		return new String(Base64.encodeBase64(b1),
				ConfigureEncryptAndDecrypt.CHAR_ENCODING);
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static String decrypt(String cryptograph, String privateKey) throws Exception {
		Key key = getPrivateKey(privateKey);
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	/**
	 * 得到公钥
	 * 
	 * @param key
	 *            密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * @param key - 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 加签
	 * @return：String 
	 * @author: LiShiJian
	 * @date: 2018-10-30 21:19
	 */
	public static String sign(String content, String privateKey) {
		String charset = ConfigureEncryptAndDecrypt.CHAR_ENCODING;
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			Signature signature = Signature.getInstance("SHA256WithRSA");
			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();
			return new String(Base64.encodeBase64(signed));
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 验证签名
	 * @return：boolean 
	 * @author: LiShiJian
	 * @date: 2018-10-30 21:20
	 */
	public static boolean checkSign(String content, String sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode2(publicKey);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));		
			Signature signature = Signature.getInstance("SHA256WithRSA");
			signature.initVerify(pubKey);
			signature.update( content.getBytes("utf-8") );
		
			boolean bverify = signature.verify(Base64.decode2(sign) );
			return bverify;			
		}catch (Exception e) {
		}
		return false;
	}
	

	
	
	public static void main(String[] args) throws Exception{		
	/*	// 加签、解签测试
		JSONObject jsonObject = JSON.parseObject("{\"phone\":\"J7WFrgV0AsJPfCl84TwrIg==\",\"payPassword\":\"dlDd5bNsqWlYcj7BXrOrnw==\",\"authCode\":\"123123\",\"wechatOpenId\":\"tdYzdvKSM61mw83U1jTwzg==\",\"source\":\"h5\",\"registerType\":\"common\",\"regPartnerCode\":\"201812011000000003\",\"bqsTokenKey\":\"A4bEcrBy4TW4SEdf72WRaMFffD74K8Mb\",\"secretKey\":\"nnoOcCKHHjnWjBRc1Q0kGBwTytwY/Am6suiWQg2H8CEjP9ToV2xtXgJ9CUsuFQdip2EYc5qUW92ywA+Ui/0Pt6czZS38VMad8gWBaF2TQNnvDsw6OEn9z4eOpngsgLs9zke9cOcvGb86XEMiDG5K7Ww6RYaGxuz0yj5sWoTtjHo=\",\"timestamp\":1546486861712,\"signType\":\"RSA2\",\"partnerCode\":\"201810201000000001\",\"sign\":\"T2mXAGWs2Q0GpmpYR4Mj4T9+OSiHMr+VfStlcPZ3APj/WjjxLmtNAsuNOVQmyQLgB7d84Nx4RE4/JDesSgO7iNAABfUnILAjzH9V8wtzAjGbNFnb8QLuZqux070XF/7XZ3fC1leP0M0ddCBoxtlUPMe+/5o6ilj8f+hVALMXCLw=\"}", Feature.OrderedField);
		String content = genSignData(jsonObject);
		System.out.println(content);
		String signStr = sign(content, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALOIM3xX/YPVlfXo9PA+D7RYVsUbu46yj4V1Ft2sPPjBni7pEOfEULsCh0buhgz1GLnCAYhwzutx3Jxj0Iq5HIizrUYlOICbZielu74stg7muqzqrKmPiA8ZnK5TdFTcg6G7mEk63PzdcHlx6z6wPndcC/tBWiTgy7qtcS3WZBnNAgMBAAECgYA6mp4727ksnL7769Rwy8FynFeOthPOa+k/pk4VhFDO3t0U8Q2Mql+z/ApOlDP77NHqaTL45+ITpzJddpOvhOeUCAw+Kvb2saRy8ODbFJEoQvUMT8IBRfxtnGw8wdawav8Cexg0YCfImJaj22vqF6hlESaVkMz3KM+dSdykM5QLnQJBAN5oMm841WgRrSMnNOvbGPlcfqFVdADOSNhCgcvESw2dyiZXOsATIumw47h70l0FYb8AxvHq78EvFzsT5D4SWe8CQQDOpiisfuanqrnw1I69rOQ9Q8R4AZ/L5vPx/fUsBMg/UXmxP2M46Su3ChI5MVKaJ53Yi6gO1Sy/5PBl1ha/H7QDAkEAtF7QuD7hY/sh7SZ41ubjhCgtUm5ZG+YwUaLuXPEe5TYYoauQrAFZCSk6JX3WBza8JQleTwf31TZ5oqHpMyXy5QJAS5QHsjbjYxUU1ljXL9Xoz+NWGtH1QJziQXIxVf0Vw9qnmETnpCvbWiFr0tEQNeMhXs6NE4exyykPJx+qyBHvQwJATsDuyC8MzJG0jiYb0FZ0OPaZz9bWg7sB/RzeJoziglmSj/1nboQPTwtbYg/JH48SMfUNkYtxBqBpns4Hg1bRXw==");
		System.out.println(signStr);
		System.out.println(checkSign(content, signStr, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCziDN8V/2D1ZX16PTwPg+0WFbFG7uOso+FdRbdrDz4wZ4u6RDnxFC7AodG7oYM9Ri5wgGIcM7rcdycY9CKuRyIs61GJTiAm2Ynpbu+LLYO5rqs6qypj4gPGZyuU3RU3IOhu5hJOtz83XB5ces+sD53XAv7QVok4Mu6rXEt1mQZzQIDAQAB"));
		*/
        Map<String, String> stringStringMap = RSAUtil.generateKeyPair();

        System.out.println(JSONObject.toJSONString(stringStringMap));


    }

}