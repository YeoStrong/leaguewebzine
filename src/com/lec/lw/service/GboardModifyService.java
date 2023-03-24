package com.lec.lw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.GboardDao;
import com.lec.lw.dto.GboardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GboardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fboardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String[] gfilenames = {"", "", ""}; 
		String[] dbFileNames = {null, null, null};
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int idx = 0;
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				gfilenames[idx] = mRequest.getFilesystemName(param);
				dbFileNames[idx] = mRequest.getParameter("dbFileName");
				if(gfilenames[idx]==null) {
					gfilenames[idx] = dbFileNames[idx];
				}
				idx++;
			}
			// mId, fTitle, fContent,  fileName, fIp
			int gnum = Integer.parseInt(mRequest.getParameter("gnum"));
			String gtitle = mRequest.getParameter("gtitle");
			String gcontent = mRequest.getParameter("gcontent");
			String gfile1 = gfilenames[2];
			String gfile2 = gfilenames[1];
			String gfile3 = gfilenames[0];
			String cname = mRequest.getParameter("cname");
			GboardDao gboardDao = GboardDao.getInstance();
			GboardDto gboardDto = new GboardDto(gnum, gtitle, gcontent, 
					gfile1, gfile2, gfile3, null, 0, 0, null, null, cname, null);
			int result = gboardDao.modifyGboard(gboardDto);
			if(result == GboardDao.SUCCESS) { 
				request.setAttribute("gboardResult", "글수정 성공");
			}else {
				request.setAttribute("gboardResult", "글수정 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("gboardResult", "글수정 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy (파일 수정을 안 했거나, 예외가 떨어질 경우 복사 안 함)
		for(String gfilename : gfilenames){
			InputStream is = null;
			OutputStream os = null;
			File serverfile = new File(path + "/" + gfilename);
			if(serverfile.exists()){
				try{
					is = new FileInputStream(serverfile);
					os = new FileOutputStream("D:/YeosongYoon/WebProgramming/Source/08_1stProject/LeagueWebzine/WebContent/gboardUp/"+gfilename);
					byte[] bs = new byte[(int)serverfile.length()];
					while(true){
						int readByteCnt = is.read(bs);
						if(readByteCnt==-1) break;
						os.write(bs, 0, readByteCnt);
					}
				}catch(Exception e){
					System.out.println(e.getMessage());
				}finally{
					try{
						if(os!=null) os.close();
						if(is!=null) is.close();
					}catch(IOException e){
						System.out.println(e.getMessage());
					}
				} // t - c - f
			} // if
		} // for
	}

}
