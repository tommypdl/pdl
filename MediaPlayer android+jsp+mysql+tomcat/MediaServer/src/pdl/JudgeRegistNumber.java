package pdl;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

//���������ж�����QQ����ʱ��ϵͳ���ܰ��ظ����Ѿ�������ĺ�����û�
public class JudgeRegistNumber {
	public void registNumber(String number) {
		// ���û��chat����ļ��У��ʹ�����
		File file0 = new File("d:\\chat");
		if (!file0.exists()) {
			file0.mkdirs();
		}
		// ���û��registNumber.txt��������洢QQ������ı��ĵ����ʹ�����
		File file1 = new File("d:\\chat\\registedNumber.txt");
		if (!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try { // ��������ɵ�QQ����д��һ���ļ���Ա��Ժ��ѯ
			RandomAccessFile ran = new RandomAccessFile(file1, "rw");
			// �õ��ļ����ܳ���
			long ranLength = ran.length();
			// ��λ���ļ�ĩβ
			ran.seek(ranLength);
			ran.write(number.getBytes());
			ran.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// �ж����ɵ�QQ�����Ƿ�����֮ǰ�Ѿ������������
	public int judgeRepeat(String number) throws Exception {
		int n = 0;
		File file = new File("d:\\chat\\registedNumber.txt");
		if (file.exists()) {
			for (int i = 0; i < file.length() / 6; i++) {
				FileInputStream fis = new FileInputStream(file);
				// ÿ��ѭ����6�����ֶ�һ�Σ���ÿ�ζ�һ��QQ���룬�����QQ����ʹ�������QQ����һ��������1�����򣬷���0
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
