package pdl;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

//此类作用判断申请QQ号码时候，系统不能把重复的已经申请过的号码给用户
public class JudgeRegistNumber {
	public void registNumber(String number) {
		// 如果没有chat这个文件夹，就创建它
		File file0 = new File("d:\\chat");
		if (!file0.exists()) {
			file0.mkdirs();
		}
		// 如果没有registNumber.txt这个用来存储QQ号码的文本文档，就创建它
		File file1 = new File("d:\\chat\\registedNumber.txt");
		if (!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try { // 把随机生成的QQ号码写到一个文件里，以备以后查询
			RandomAccessFile ran = new RandomAccessFile(file1, "rw");
			// 得到文件的总长度
			long ranLength = ran.length();
			// 定位到文件末尾
			ran.seek(ranLength);
			ran.write(number.getBytes());
			ran.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 判断生成的QQ号码是否在这之前已经被人申请过了
	public int judgeRepeat(String number) throws Exception {
		int n = 0;
		File file = new File("d:\\chat\\registedNumber.txt");
		if (file.exists()) {
			for (int i = 0; i < file.length() / 6; i++) {
				FileInputStream fis = new FileInputStream(file);
				// 每次循环隔6个数字读一次，即每次读一个QQ号码，如果有QQ号码和传进来的QQ号码一样，返回1，否则，返回0
				fis.skip(6 * i);
				byte[] buffer = new byte[6];
				fis.read(buffer);
				String str = new String(buffer);
				if (number.equals(str)) {
					n++;
				}
			}
		}
		return n;
	}
}
