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

import com.lec.lw.dao.NboardDao;
import com.lec.lw.dto.AdminDto;
import com.lec.lw.dto.NboardDto;
import com.lec.lw.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NboardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("nboardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String nimage = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", 
												new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nimage = mRequest.getFilesystemName(param);
			HttpSession httpSession = request.getSession();
			AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
			if(admin!=null) {
				String aid = admin.getAid(); // 로그인 한 사람의 aid
				String ntitle = mRequest.getParameter("ntitle");
				String ncontent = mRequest.getParameter("ncontent");
				String anickname = mRequest.getParameter("anickname");
				NboardDao nboardDao = NboardDao.getInstance();
				NboardDto nboardDto = new NboardDto(0, ntitle, ncontent, nimage, null, 0, aid, anickname);
				int result = nboardDao.writeNboard(nboardDto);
				// joinMember결과에 따라 적절히 request.setAttribute
				if(result == NboardDao.SUCCESS) { // 회원가입 진행
					request.setAttribute("nboardResult", "공지 글쓰기 성공");
				}else {
					request.setAttribute("nboardResult", "공지 글쓰기 실패");
				}
			}else {
				request.setAttribute("nboardResult", "로그인 한 사람만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("nboardResult", "공지 글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		if(nimage!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+nimage);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/YeosongYoon/WebProgramming/Source/08_1stProject/LeagueWebzine/WebContent/nboardUp/"+nimage);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} // try
		}// 파일 복사 if
	}

}
