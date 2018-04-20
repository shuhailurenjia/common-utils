package com.zwh.common.passport;

import java.nio.ByteBuffer;
import java.util.Random;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

/**
 * User: Cougar Date: 14-5-11 Time: 下午6:16
 */
public class TokenUtil {

	/**
	 * 游客userId
	 */
	public static final String guestId = "00000000000000000000000000000000";

	private static final Random random = new Random();

	private static DESCoding des = null;

	static {
		try {
			des = new DESCoding("COUGAR(<(*lvjGa*Hbat>@(f".getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成guest(未验证角色)用户token
	 *
	 * @param authFlag
	 * @param deviceId
	 * @return
	 */
	public static String encodeTokenForGuest(AuthFlag authFlag, String deviceId) {

		return encodeToken(authFlag.getValue(), RoleFlag.InvalidRole.getValue(), guestId, deviceId);
	}

	/**
	 * 生成登录用户token
	 *
	 * @param uid
	 * @param deviceId
	 * @return
	 */
	public static String encodeTokenForUser(RoleFlag roleFlag, String uid, String deviceId) {
		return encodeToken(AuthFlag.VALID_USER.getValue(), roleFlag.getValue(), uid, deviceId);

	}

	/**
	 * 加密token
	 *
	 * @param auth
	 * @param role
	 * @param userId
	 * @param deviceId
	 * @return
	 */
	public static String encodeToken(int auth, int role, String userId, String deviceId) {
		String token = null;
		if (userId == null || deviceId == null || userId.length() != 32 || deviceId.length() != 32) {
			return token;
		}
		int crtTime = (int) (System.currentTimeMillis() / 1000); // 精确到秒的crtTime
		// 初始化byte
		ByteBuffer buf = ByteBuffer.allocate(140);
		buf.putInt(auth);
		buf.putInt(role);
		// 写入userid
		for (char a : userId.toCharArray()) {
			buf.putChar(a);
		}
		// 写入deviceId
		for (char a : deviceId.toCharArray()) {
			buf.putChar(a);
		}
		// 记录时间戳
		buf.putInt(crtTime);
		buf.flip();

		// des encode
		byte[] encodeBytes = des.encode(buf.array());
		if (encodeBytes == null) {
			return token;
		}

		// 混入随机数
		byte[] realBytes = new byte[146];
		byte[] plus = new byte[2];
		plus[0] = 1;
		plus[1] = (byte) random.nextInt(1234567890);
		// 中间16byte为实际数据，前缀加1byte sid版本，后缀加1byte 随机数
		System.arraycopy(plus, 0, realBytes, 0, 1);
		System.arraycopy(reverse(encodeBytes), 0, realBytes, 1, 144);
		System.arraycopy(plus, 1, realBytes, 145, 1);
		// 生成base64字符串
		token = StringUtils.newStringUtf8(Base64.encodeBase64URLSafe(realBytes));
		return token;
	}

	/**
	 * 解密token串
	 */
	@SuppressWarnings("deprecation")
	public static TokenInfo decodeToken(String token) {

		if (token == null || token.trim().isEmpty()) {
			return null;
		}
		TokenInfo tokenInfo = new TokenInfo();
		// super base64 decode
		byte[] base64Bytes = Base64.decodeBase64(token.getBytes(Charsets.UTF_8));
		if (base64Bytes == null) {
			return null;
		}

		// 获取第一byte，得到sid版本，倒数第一byte，随机数，忽略
		if (base64Bytes == null || base64Bytes.length != 146) {
			return null;
		}
		// 排除随机数
		byte version = base64Bytes[0];
		if (version != 1) {
			return null;
		}
		// byte randNum = base64Bytes[145];

		byte[] realBytes = new byte[144];
		// 中间136byte为实际数据
		System.arraycopy(base64Bytes, 1, realBytes, 0, 144);
		// des decode
		byte[] decodeBytes = des.decode(reverse(realBytes));
		if (decodeBytes == null || decodeBytes.length != 140) {
			return null;
		}

		// convert data to int array
		ByteBuffer buf = ByteBuffer.wrap(decodeBytes);
		int auth = buf.getInt();
		int role = buf.getInt();
		StringBuilder userId = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			userId.append(buf.getChar());
		}

		StringBuilder deviceId = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			deviceId.append(buf.getChar());
		}

		int crtTime = buf.getInt();
		tokenInfo.setAuth(AuthFlag.valueOf(auth));
		tokenInfo.setRole(RoleFlag.valueOf(role));
		tokenInfo.setUid(userId.toString());
		tokenInfo.setDid(deviceId.toString());
		tokenInfo.setCrtTime(crtTime);
		return tokenInfo;
	}
	/**
	 * 固定字节倒序 后续可改为 奇偶位互换
	 */
	private static byte[] reverse(byte[] myByte) {
		byte[] newByte = new byte[myByte.length];
		for (int i = 0; i < myByte.length; i++) {
			newByte[i] = myByte[myByte.length - 1 - i];
		}
		return newByte;
	}

	public static void main(String[] args) {
		String token = "AT3cF6BHw22shXxAXkSuRCXdDX0OOqCBU_-nU4Fb8X16SA0WuAQzwqkw0G8W1RyD7Xwy8hzHCEHI2C92ZiXy07W0LKoC_cSiRJiOlzQ0TEl3OSvWkuv__lGtoJ_L_sf6Lv8PyN-FXEwz0kPEJVkANb-bOfnuFpX0-YTF7LzZD7cV1yFFPJ8duoKrUwGkF4-ZOog";

		TokenInfo decodeToken = TokenUtil.decodeToken(token);

		System.out.println(decodeToken);
	}

}
