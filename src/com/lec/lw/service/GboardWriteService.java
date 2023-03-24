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
import javax.servlet.http.HttpSession;

import com.lec.lw.dao.GboardDao;
import com.lec.lw.dto.GboardDto;
import com.lec.lw.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GboardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("gboardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String[] gfilenames = {"", "", ""};
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", 
												new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int idx = 0;
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				gfilenames[idx] = mRequest.getFilesystemName(param);
				idx++;
			}
			HttpSession httpSession = request.getSession();
			MemberDto member = (MemberDto)httpSession.getAttribute("member");
			if(member!=null) {
				String mid = member.getMid(); // 로그인 한 사람의 mid
				String gtitle = mRequest.getParameter("gtitle");
				String gcontent = mRequest.getParameter("gcontent");
				String mnickname = mRequest.getParameter("mnickname");
				String cname = mRequest.getParameter("cname");
				String gip = request.getRemoteAddr();
				String gfile1 = gfilenames[2];
				String gfile2 = gfilenames[1];
				String gfile3 = gfilenames[0];
				GboardDao gboardDao = GboardDao.getInstance();
				GboardDto gboardDto = new GboardDto(0, gtitle, gcontent, 
						gfile1, gfile2, gfile3, null, 0, 0, mid, mnickname, cname, gip);
				int result = gboardDao.writeGboard(gboardDto);
				// joinMember결과에 따라 적절히 request.setAttribute
				if(result == GboardDao.SUCCESS) { // 회원가입 진행
					request.setAttribute("gboardResult", "글쓰기 성공");
				}else {
					request.setAttribute("gboardResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("gboardResult", "로그인 한 사람만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("gboardResult", "글쓰기 실패");
		}
		//  서버로 올려진 파일을 소스 폴더로 복사
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
