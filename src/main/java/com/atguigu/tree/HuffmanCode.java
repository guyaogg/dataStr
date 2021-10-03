package com.atguigu.tree;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 顾遥
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // 测试压缩文件
        /*String srcFile = "F:\\test\\kk.xml";
        String dstFile = "F:\\test\\kk_zip.huffmanZip";
        zipFile(srcFile, dstFile);*/
        // 测试解压文件
        String zipFile = "F:\\test\\kk_zip.huffmanZip";
        String dstFile = "F:\\test\\kk2.xml";
        unZipFile(zipFile, dstFile);
       /* String str = "i like like like java do you like a java";
        byte[] bytes = huffmanZip(str);
        System.out.println("压缩前长度：" + str.getBytes().length);
        System.out.println("压缩后长度：" + bytes.length);
        // 测试 byteToBitString
//        System.out.println(byteToBitString(true,(byte) 1));
        byte[] decode = decode(huffmanCode, bytes);
        System.out.println(new String(decode));*/


    }
    // 解压流程
    // 1.将压缩数据先转成赫夫曼编码对应的二进制的字符串
    // 2.对照赫夫曼表转化二进制

    /**
     * 解压文件
     *
     * @param zipFile 霍夫曼的压缩文件
     * @param dstFile 解压地址
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件的输入流
        try (InputStream is = new FileInputStream(zipFile);
             ObjectInputStream ois = new ObjectInputStream(is);
             OutputStream os = new FileOutputStream(dstFile);
        ) {
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte,String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os.write(bytes);
            System.out.println("解压文件成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解压文件失败");

        }
    }

    /**
     * 使用赫夫曼压缩
     *
     * @param srcFile 压缩文件的全路径
     * @param dstFile 我们压缩后的文件目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        try (FileInputStream is = new FileInputStream(srcFile);
             FileOutputStream os = new FileOutputStream(dstFile);
             ObjectOutputStream oos = new ObjectOutputStream(os);

        ) {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            byte[] huffmanBytes = huffmanZip(bytes);
            // 创建文件关联的 ObjectOutputStream
            // 把赫夫曼编码字节数据写入文件
            oos.writeObject(huffmanBytes);
            // 以对象流方式写入 赫夫曼编码 ，为了恢复源文件时使用
            // 注意一定要将编码表写入压缩文件，不然恢复不了
            oos.writeObject(huffmanCode);
            System.out.println("压缩文件成功");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("压缩文件失败");

        }

    }

    /**
     * 对压缩数据解压
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 未压缩的原来字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
//        System.out.println(Arrays.toString(huffmanBytes));

        // 1.先得到对应二进制字符串
        StringBuilder builder = new StringBuilder();
        // 2.转换 byte[]
        int i = 1;
        for (byte huffmanByte : huffmanBytes) {
            boolean flag = i++ < huffmanBytes.length;
            builder.append(byteToBitString(flag, huffmanByte).intern());
        }
//        System.out.println(builder.length());
        // 把字符串解码
        // 反转 map
        Map<String, Byte> map = new HashMap<>(huffmanCodes.size());
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {

            map.put(entry.getValue(), entry.getKey());
        }
        // 创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int j = 0; j < builder.length(); ) {
            // 小的计数器
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 取出一个 '1'|'0'
                String key = builder.substring(j, j + count);
                b = map.get(key);
                if (b != null) {
                    break;
                }
                count++;
            }
            j = j + count;
            list.add(b);

        }
        byte[] bytes = new byte[list.size()];
        AtomicInteger iAtomic = new AtomicInteger();
        list.forEach((b) -> {
            bytes[iAtomic.getAndIncrement()] = b;
        });
        return bytes;
    }

    /**
     * 将一个 byte 转成二进制的字符串
     *
     * @param b    要转的 byte
     * @param flag 标识是否补高位 true 补高位，
     * @return 该 b 对应的二进制字符串（按照补码返回
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b;
        if (flag) {
            temp |= 256;

        }
        String str = Integer.toBinaryString(temp); // 返回的是 temp 二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);

        } else {
            return str;
        }

    }

    /**
     * 赫夫曼编码压缩
     *
     * @param str 要压缩的字符串
     * @return 压缩后的数组
     */
    public static byte[] huffmanZip(String str) {
        byte[] bytes = str.getBytes();
//        System.out.println(bytes.length);
        return huffmanZip(bytes);
    }

    /**
     * 赫夫曼编码压缩
     *
     * @param bytes 要压缩的流
     * @return 压缩后的数组
     */
    public static byte[] huffmanZip(byte[] bytes) {

        List<Node<Byte>> nodes = getNodes(bytes);
//        System.out.println("nodes=" + nodes);

        Node<Byte> huffmanTree = createHuffmanTree(nodes);
//        huffmanTree.preOrder();

        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
//        System.out.println("~生成赫夫曼编码表" + huffmanCodes);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
        return huffmanCodeBytes;
    }


    /**
     * 压缩编码 ， 注意编码转化
     *
     * @param bytes        压缩流对象
     * @param huffmanCodes 编码表
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1.利用 huffmanCodes 将 bytes 转为赫夫曼对应的字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b).intern());
        }
//        System.out.println("测试：sb=" + sb);
        // 返回 byte[] 长度
        int len, byteLen = 8;
        len = (sb.length() + 7) / byteLen;
        // 创建压缩后的 byte[]
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += byteLen) {
            String strByte;
            strByte = sb.substring(i, Math.min(i + 8, sb.length()));

            // 将 strByte 转成 byte ，放入 by
            by[index++] = (byte) Integer.parseInt(strByte, 2);

        }
        return by;

    }

    /**
     * 创建对应赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node<Byte> createHuffmanTree(List<Node<Byte>> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到达
            Collections.sort(nodes);

            Node<Byte> leftNode = nodes.get(0);
            Node<Byte> rightNode = nodes.get(1);

            // 创建新的二叉树，根节点只有权值没有数据
            Node<Byte> parent = new Node(leftNode.weight + rightNode.weight);
            parent.right = rightNode;
            parent.left = leftNode;
            nodes.remove(0);
            nodes.remove(0);

            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * * Map<Byte,String> 形式 如 u=1010
     */
    static Map<Byte, String> huffmanCode = new HashMap<>();
    /**
     * StringBuilder 构建赫夫曼编码
     */
    static StringBuilder stringBuilder = new StringBuilder();


    private static Map<Byte, String> getCodes(Node<Byte> node) {
        if (node == null) {
            return null;
        }
        getCodes(node, "", stringBuilder);
        return huffmanCode;

    }

    /**
     * 将传入的 node 的霍夫曼编码得到，并放入huffmanCode集合中
     *
     * @param node 节点
     * @param code 编码
     * @param sb   拼接
     */
    private static void getCodes(Node<Byte> node, String code, StringBuilder sb) {
        StringBuilder builder = new StringBuilder(sb);
        builder.append(code);
        if (node != null) {
            // 无用数据
            if (node.data == null) {
                getCodes(node.left, "0", builder);
                getCodes(node.right, "1", builder);

            } else {
                huffmanCode.put(node.data, builder.toString().intern());
            }
        }
    }

    /**
     * 创建对应赫夫曼节点
     *
     * @param bytes
     * @return
     */
    private static List<Node<Byte>> getNodes(byte[] bytes) {
        // 创建一个 list
        List<Node<Byte>> nodes = new ArrayList<>();

        // 遍历bytes,统计 byte 次数并存储 node
        Map<Byte, Integer> counts = new HashMap<>(bytes.length);
        for (Byte b : bytes) {
            counts.put(b, counts.getOrDefault(b, 0) + 1);
        }
        // 把每一个键值转化为 Node 对象 ， 并加入 nodes
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getValue(), entry.getKey()));

        }
        return nodes;
    }
}
