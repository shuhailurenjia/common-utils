package com.zwh.common.util;

/**
 * Created with IntelliJ IDEA. User: Cougar Date: 14-12-5 Time: 下午4:12 To change this template use File | Settings | File Templates.
 */
public class TransactionCodeEncryptUtil {

    /// <summary>
    /// 映射表
    /// </summary>
    private static
    Byte[][]
            __encryptArray =
            new Byte[][]{new Byte[]{(byte) 47, (byte) 208, (byte) 206, (byte) 236, (byte) 184, (byte) 205, (byte) 1, (byte) 193, (byte) 222, (byte) 125, (byte) 74, (byte) 232, (byte) 17, (byte) 73,
                    (byte) 198, (byte) 20, (byte) 106, (byte) 37, (byte) 174, (byte) 173, (byte) 76, (byte) 67, (byte) 204, (byte) 3, (byte) 196, (byte) 77, (byte) 254, (byte) 219,
                    (byte) 163, (byte) 229, (byte) 32, (byte) 31, (byte) 141, (byte) 71, (byte) 181, (byte) 62, (byte) 100, (byte) 120, (byte) 188, (byte) 24, (byte) 252, (byte) 111,
                    (byte) 49, (byte) 15, (byte) 75, (byte) 225, (byte) 4, (byte) 170, (byte) 94, (byte) 145, (byte) 131, (byte) 61, (byte) 28, (byte) 86, (byte) 64, (byte) 221,
                    (byte) 95, (byte) 248, (byte) 27, (byte) 243, (byte) 66, (byte) 84, (byte) 124, (byte) 179, (byte) 194, (byte) 93, (byte) 134, (byte) 247, (byte) 87, (byte) 152,
                    (byte) 56, (byte) 132, (byte) 12, (byte) 117, (byte) 127, (byte) 140, (byte) 104, (byte) 142, (byte) 233, (byte) 51, (byte) 244, (byte) 10, (byte) 171, (byte) 187,
                    (byte) 190, (byte) 139, (byte) 246, (byte) 147, (byte) 250, (byte) 180, (byte) 90, (byte) 122, (byte) 148, (byte) 80, (byte) 154, (byte) 16, (byte) 209, (byte) 96,
                    (byte) 240, (byte) 19, (byte) 25, (byte) 176, (byte) 218, (byte) 230, (byte) 245, (byte) 212, (byte) 223, (byte) 203, (byte) 40, (byte) 216, (byte) 128, (byte) 114,
                    (byte) 136, (byte) 36, (byte) 239, (byte) 118, (byte) 13, (byte) 18, (byte) 43, (byte) 238, (byte) 166, (byte) 177, (byte) 2, (byte) 60, (byte) 234, (byte) 119,
                    (byte) 135, (byte) 249, (byte) 149, (byte) 151, (byte) 217, (byte) 165, (byte) 116, (byte) 5, (byte) 138, (byte) 91, (byte) 168, (byte) 123, (byte) 237, (byte) 191,
                    (byte) 48, (byte) 192, (byte) 185, (byte) 103, (byte) 242, (byte) 7, (byte) 150, (byte) 241, (byte) 161, (byte) 88, (byte) 33, (byte) 65, (byte) 58, (byte) 22,
                    (byte) 72, (byte) 11, (byte) 129, (byte) 220, (byte) 68, (byte) 45, (byte) 42, (byte) 210, (byte) 79, (byte) 44, (byte) 107, (byte) 6, (byte) 214, (byte) 197,
                    (byte) 115, (byte) 253, (byte) 215, (byte) 235, (byte) 231, (byte) 109, (byte) 50, (byte) 126, (byte) 14, (byte) 227, (byte) 9, (byte) 224, (byte) 175, (byte) 30,
                    (byte) 0, (byte) 53, (byte) 155, (byte) 213, (byte) 63, (byte) 159, (byte) 110, (byte) 146, (byte) 108, (byte) 102, (byte) 82, (byte) 211, (byte) 41, (byte) 105,
                    (byte) 101, (byte) 157, (byte) 186, (byte) 52, (byte) 57, (byte) 39, (byte) 70, (byte) 167, (byte) 29, (byte) 162, (byte) 195, (byte) 251, (byte) 8, (byte) 38,
                    (byte) 55, (byte) 130, (byte) 226, (byte) 59, (byte) 69, (byte) 112, (byte) 54, (byte) 46, (byte) 153, (byte) 200, (byte) 35, (byte) 202, (byte) 83, (byte) 81,
                    (byte) 26, (byte) 182, (byte) 156, (byte) 34, (byte) 228, (byte) 85, (byte) 99, (byte) 21, (byte) 133, (byte) 172, (byte) 89, (byte) 160, (byte) 97, (byte) 189,
                    (byte) 143, (byte) 92, (byte) 78, (byte) 144, (byte) 255, (byte) 158, (byte) 201, (byte) 183, (byte) 199, (byte) 178, (byte) 169, (byte) 164, (byte) 113, (byte) 98,
                    (byte) 121, (byte) 137, (byte) 23, (byte) 207},
                    new Byte[]{(byte) 172, (byte) 243, (byte) 111, (byte) 201, (byte) 170, (byte) 176, (byte) 195, (byte) 59, (byte) 219, (byte) 144, (byte) 90, (byte) 44, (byte) 141, (byte) 63,
                            (byte) 117, (byte) 254, (byte) 126, (byte) 171, (byte) 242, (byte) 53, (byte) 178, (byte) 161, (byte) 203, (byte) 2, (byte) 119, (byte) 229, (byte) 1, (byte) 73,
                            (byte) 173, (byte) 86, (byte) 25, (byte) 132, (byte) 56, (byte) 24, (byte) 149, (byte) 113, (byte) 26, (byte) 11, (byte) 248, (byte) 57, (byte) 40, (byte) 14,
                            (byte) 241, (byte) 208, (byte) 224, (byte) 109, (byte) 189, (byte) 19, (byte) 231, (byte) 166, (byte) 239, (byte) 81, (byte) 215, (byte) 107, (byte) 157,
                            (byte) 226, (byte) 92, (byte) 6, (byte) 214, (byte) 250, (byte) 187, (byte) 177, (byte) 102, (byte) 110, (byte) 129, (byte) 58, (byte) 112, (byte) 251, (byte) 52,
                            (byte) 235, (byte) 12, (byte) 4, (byte) 167, (byte) 194, (byte) 108, (byte) 32, (byte) 232, (byte) 60, (byte) 48, (byte) 50, (byte) 191, (byte) 49, (byte) 192,
                            (byte) 35, (byte) 184, (byte) 212, (byte) 17, (byte) 162, (byte) 94, (byte) 143, (byte) 0, (byte) 95, (byte) 72, (byte) 228, (byte) 13, (byte) 164, (byte) 163,
                            (byte) 3, (byte) 21, (byte) 159, (byte) 137, (byte) 124, (byte) 31, (byte) 46, (byte) 29, (byte) 51, (byte) 45, (byte) 140, (byte) 27, (byte) 174, (byte) 71,
                            (byte) 20, (byte) 182, (byte) 7, (byte) 181, (byte) 180, (byte) 8, (byte) 205, (byte) 85, (byte) 79, (byte) 68, (byte) 146, (byte) 78, (byte) 98, (byte) 236,
                            (byte) 76, (byte) 190, (byte) 103, (byte) 168, (byte) 217, (byte) 255, (byte) 245, (byte) 202, (byte) 247, (byte) 10, (byte) 240, (byte) 158, (byte) 233,
                            (byte) 169, (byte) 104, (byte) 37, (byte) 130, (byte) 39, (byte) 223, (byte) 238, (byte) 216, (byte) 148, (byte) 197, (byte) 207, (byte) 199, (byte) 100,
                            (byte) 153, (byte) 220, (byte) 147, (byte) 249, (byte) 160, (byte) 66, (byte) 5, (byte) 185, (byte) 87, (byte) 204, (byte) 97, (byte) 116, (byte) 135, (byte) 222,
                            (byte) 225, (byte) 121, (byte) 80, (byte) 131, (byte) 41, (byte) 122, (byte) 18, (byte) 218, (byte) 33, (byte) 34, (byte) 152, (byte) 246, (byte) 145, (byte) 237,
                            (byte) 99, (byte) 150, (byte) 89, (byte) 179, (byte) 88, (byte) 230, (byte) 67, (byte) 156, (byte) 142, (byte) 154, (byte) 136, (byte) 64, (byte) 70, (byte) 93,
                            (byte) 123, (byte) 61, (byte) 75, (byte) 91, (byte) 55, (byte) 198, (byte) 175, (byte) 186, (byte) 221, (byte) 183, (byte) 69, (byte) 196, (byte) 155, (byte) 128,
                            (byte) 133, (byte) 213, (byte) 65, (byte) 211, (byte) 43, (byte) 74, (byte) 120, (byte) 101, (byte) 234, (byte) 83, (byte) 139, (byte) 253, (byte) 106, (byte) 127,
                            (byte) 138, (byte) 62, (byte) 105, (byte) 9, (byte) 134, (byte) 200, (byte) 42, (byte) 15, (byte) 209, (byte) 188, (byte) 84, (byte) 252, (byte) 193, (byte) 23,
                            (byte) 30, (byte) 82, (byte) 77, (byte) 210, (byte) 36, (byte) 115, (byte) 151, (byte) 118, (byte) 16, (byte) 54, (byte) 227, (byte) 22, (byte) 47, (byte) 165,
                            (byte) 28, (byte) 38, (byte) 125, (byte) 114, (byte) 244, (byte) 206, (byte) 96}};


    /// <summary>
    /// 通过映射表获取对应的Byte
    /// </summary>
    /// <param name="bt"></param>
    /// <returns></returns>
    public static byte GetEncryptByte(byte bt) throws Exception {
        for (int i = 0; i < 256; i++) {
            if (__encryptArray[0][i] == bt) {
                return __encryptArray[1][i];
            }
        }
        throw new Exception("Can not find the index value of encrypt array for value : " + bt);
    }

    /// <summary>
    /// 通过映射表获取对应的原始Byte
    /// </summary>
    /// <param name="bt"></param>
    /// <returns></returns>
    public static byte GetDecryptByte(byte bt) throws Exception {
        for (int i = 0; i < 256; i++) {
            if (__encryptArray[1][i] == bt) {
                return __encryptArray[0][i];
            }
        }
        throw new Exception("Can not find the index value of encrypt array for value : " + bt);
    }


    public static byte[] longToBytes(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }

    public static long bytesToLong(byte[] array) {
        return ((((long) array[0] & 0xff) << 56) | (((long) array[1] & 0xff) << 48) | (((long) array[2] & 0xff) << 40) | (((long) array[3] & 0xff) << 32) | (((long) array[4] & 0xff) << 24) | (
                ((long) array[5] & 0xff) << 16) | (((long) array[6] & 0xff) << 8) | (((long) array[7] & 0xff) << 0));
    }


    /**
     * 获取编码后的订单ID
     */
    public static Long EncryptOrderID(Long orderID) throws Exception {
        byte[] array = longToBytes(orderID);
        for (int i = 4; i < 8; i++) {
            byte c = array[i];
            array[i] = GetEncryptByte((byte) (c & 0xFF));
        }
        byte tempByte = array[7]; // 置换第五位和第七位
        array[7] = array[5];
        array[5] = tempByte;
        return bytesToLong(array);
    }

    /**
     * 通过编码后的订单ID获取编码前的订单ID
     */
    public static Long DecryptOrderID(Long orderIDgen) throws Exception {
        byte[] array = longToBytes(orderIDgen);
        byte tempByte = array[5];
        array[5] = array[7];
        array[7] = tempByte;
        for (int i = 4; i < 8; i++) {
            byte c = array[i];
            array[i] = GetDecryptByte((byte) (c & 0xFF));
        }
        return bytesToLong(array);
    }


//    public static void main(String[] args) throws Exception {
//        Long orderId = 47897357L;
//        Long newId = TransactionCodeEncryptUtil.EncryptOrderID(orderId);
//        System.out.println("newId:" + newId);
//        System.out.println("oldId:" + TransactionCodeEncryptUtil.DecryptOrderID(newId));
//
//    }

}
