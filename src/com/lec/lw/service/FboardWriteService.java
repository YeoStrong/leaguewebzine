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

import com.lec.lw.dao.FboardDao;
import com.lec.lw.dto.FboardDto;
import com.lec.lw.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FboardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fboardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String fimage = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", 
												new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fimage = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession httpSession = request.getSession();
			MemberDto member = (MemberDto)httpSession.getAttribute("member");
			if(member!=null) {
				String mid = member.getMid(); // 로그인 한 사람의 mId
				String ftitle = mRequest.getParameter("ftitle");
				String fcontent = mRequest.getParameter("fcontent");
				String mnickname = mRequest.getParameter("mnickname");
				String fip = request.getRemoteAddr();
				FboardDao fboardDao = FboardDao.getInstance();
				FboardDto fboardDto = new FboardDto(0, ftitle, fcontent, fimage, null, 0, 
													0, 0, 0, mid, mnickname, fip);
				int result = fboardDao.writeFboard(fboardDto);
				// joinMember결과에 따라 적절히 request.setAttribute
				if(result == FboardDao.SUCCESS) { // 회원가입 진행
					request.setAttribute("fboardResult", "글쓰기 성공");
				}else {
					request.setAttribute("fboardResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("fboardResult", "로그인 한 사람만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("fboardResult", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		if(fimage!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+fimage);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/YeosongYoon/WebProgramming/Source/08_1stProject/LeagueWebzine/WebContent/fboardUp/"+fimage);
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
