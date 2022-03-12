package textgame;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HandlerSave {
	
	public void savePlayer(Player p){//����ҽ�ɫ��Ϣ���д浵
		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream("save.txt"))));//���������
			
			String[] playerdata = p.file();
			for(int i=0;i<playerdata.length;i++) {
				out.println(playerdata[i]);//���ַ���������ʽ������Ϣ��д���ĵ�
			}
			
			out.close();
			System.out.println("����ɹ�����ɫ��Ϣ�ѱ���");
		} catch (FileNotFoundException e) {
			System.out.println("�浵ʧ��");
		}
	}

}
