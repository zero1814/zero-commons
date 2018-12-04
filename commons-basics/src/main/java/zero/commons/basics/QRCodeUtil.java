package zero.commons.basics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * 类: QRCodeUtil <br>
 * 描述: 二维码生成 <br>
 * 作者: zhy<br>
 * 时间: 2018年7月27日 下午4:25:19
 */
public class QRCodeUtil {

	private String imageType = "JPEG";

	private int size = 200;

	public QRCodeUtil() {

	}

	public QRCodeUtil(String imageType, int size) {
		this.imageType = imageType;
		this.size = size;
	}

	/**
	 * 
	 * 方法: create <br>
	 * 描述: 创建二维码 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年7月27日 下午5:18:16
	 * 
	 * @param out     输出流
	 * @param content 存储内容
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public boolean create(OutputStream out, String content) throws WriterException, IOException {
		// 设置二维码纠错级别MAP
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);// 矫错级别
		QRCodeWriter writer = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix byteMatrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size, hintMap);
		// 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// 使用比特矩阵画并保存图像
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i - 100, j - 100, 1, 1);
				}
			}
		}
		return ImageIO.write(image, imageType, out);
	}

	/**
	 * 
	 * 方法: create <br>
	 * 描述: 创建二维码 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年7月27日 下午5:18:23
	 * 
	 * @param path    文件路径
	 * @param content 存储内容
	 * @return
	 */
	public boolean create(String path, String content) {
		boolean flag = false;
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(path));
			flag = create(out, content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * 方法: read <br>
	 * 描述: 读取二维码中的内容 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年7月27日 下午4:49:23
	 * 
	 * @param in 输入流
	 * @return
	 */
	public String read(InputStream in) {
		String content = null;
		try {
			// 从输入流中获取字符串信息
			BufferedImage image = ImageIO.read(in);
			// 将图像转换为二进制位图源
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			QRCodeReader reader = new QRCodeReader();
			Result result = reader.decode(bitmap);
			content = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 
	 * 方法: read <br>
	 * 描述: 读取二维码中的内容 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年7月27日 下午5:18:00
	 * 
	 * @param file 文件路径
	 * @return
	 */
	public String read(String file) {
		String content = null;
		InputStream in = null;
		try {
			in = new FileInputStream(new File(file));
			content = read(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
