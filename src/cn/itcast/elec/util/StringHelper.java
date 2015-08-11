package cn.itcast.elec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 * @Name: stringConvertDate
 * @Description: ���ַ�����ʽ������ת������������
 * @Author: �����ң����ߣ�
 * @Version: V1.00 ���汾�ţ�
 * @Create Date: 2015-07-30 ���������ڣ�
 * @Parameters: String date �ַ������͵�������ʽ
 * @Return: Date ��������
 */
public class StringHelper {
	public static Date stringConvertDate(String textDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(textDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
