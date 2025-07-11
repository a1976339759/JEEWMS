package com.base.modules.baseUtil;

import org.springframework.util.StringUtils;

import java.io.*;


public class BarcodeUtil {
	  /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
 
    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }
 
    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
 
//        Code39Bean bean = new Code39Bean();
//        PDF417Bean bean=new PDF417Bean();
//        POSTNETBean bean =new POSTNETBean();
//        Interleaved2Of5Bean bean =new Interleaved2Of5Bean();
//        EAN128Bean bean =new EAN128Bean();
       /* Code128Bean bean =new Code128Bean();
        // 精细度
        final int dpi = 250;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(2.0f / dpi);
 
        // 配置对象
        bean.setModuleWidth(moduleWidth);
//        bean.setWideFactor(3);
        bean.doQuietZone(false);
 
        String format = "image/png";
        try {
 
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            // 生成条形码
            bean.generateBarcode(canvas, msg);
 
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
 
    public static void main(String[] args) {
        String msg = "123456789asdfxXW12";
        String path = "D:/barcode.png";
        generateFile(msg, path);
    }
    public static ByteArrayInputStream generateToStream(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        
        ByteArrayOutputStream   baos=new   ByteArrayOutputStream();
        baos=(ByteArrayOutputStream) ous;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
//        return ous;
    }
}
