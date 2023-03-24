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

public class FboardReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fboardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String ffileName = "";
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			ffileName = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession httpSession = request.getSession();
			MemberDto member = (MemberDto)httpSession.getAttribute("member");
			if(member ==null) {
				request.setAttribute("fboardResult", "로그인 후에만 답글쓰기 가능");
				return;
			}
			String mid = member.getMid();
			String ftitle = mRequest.getParameter("ftitle");
			String fcontent = mRequest.getParameter("fcontent");
			String fip = request.getRemoteAddr();
			int fgroup = Integer.parseInt(mRequest.getParameter("fgroup"));
			int fstep = Integer.parseInt(mRequest.getParameter("fstep"));
			int findent = Integer.parseInt(mRequest.getParameter("findent"));
			FboardDao fboardDao = FboardDao.getInstance();
			FboardDto fboardDto = new FboardDto(0, ftitle, fcontent, ffileName, null, 0, fgroup, fstep, findent, mid, null, fip);
			int result = fboardDao.replyFboard(fboardDto);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == FboardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("fboardResult", "답글쓰기 성공");
			}else {
				request.setAttribute("fboardResult", "답글쓰기 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("fboardResult", "답글쓰기 실패");
		}
		// 서버에 올라간 fboardUp 파일을 소스폴더에 filecopy
		if(ffileName!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+ffileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/YeosongYoon/WebProgramming/Source/08_1stProject/LeagueWebzine/WebContent/fboardUp/"+ffileName);
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