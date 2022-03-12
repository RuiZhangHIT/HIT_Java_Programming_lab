package textgame;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HandlerSave {
	
	public void savePlayer(Player p){//对玩家角色信息进行存档
		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream("save.txt"))));//定义输出流
			
			String[] playerdata = p.file();
			for(int i=0;i<playerdata.length;i++) {
				out.println(playerdata[i]);//以字符串数组形式接收信息，写入文档
			}
			
			out.close();
			System.out.println("保存成功，角色信息已保存");
		} catch (FileNotFoundException e) {
			System.out.println("存档失败");
		}
	}

}
