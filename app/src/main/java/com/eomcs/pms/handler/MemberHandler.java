package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberHandler {

  static final int LENGTH = 100;

  Member[] members = new Member[LENGTH];  // 레퍼런스 배열 준비  
  int size = 0;

  public void add() {
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.no = Prompt.inputInt("번호? ");
    m.name = Prompt.inputString("이름? ");
    m.email = Prompt.inputString("이메일? ");
    m.password = Prompt.inputString("암호? ");
    m.photo = Prompt.inputString("사진? ");
    m.tel = Prompt.inputString("전화? ");
    m.registeredDate = new java.sql.Date(System.currentTimeMillis());

    this.members[this.size++] = m;
  }

  public void list() {
    System.out.println("[회원 목록]");

    for (int i = 0; i < this.size; i++) {
      Member m = this.members[i];
      if(this.members[i] != null) continue;
      // 번호, 이름, 이메일, 전화, 가입일
      System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
          m.no, m.name, m.email, m.tel, m.registeredDate);
    }
  }

  public boolean exist(String name) {
    for (int i = 0; i < this.size; i++) {
      if (name.equals(this.members[i].name)) {
        return true;
      }
    }
    return false;
  }

  public void update() {
    System.out.println("[멤버 정보 수정]");
    int num = Prompt.inputInt("번호 : ");

    Member m = findByNo(num);
    if(m == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String currentName = Prompt.inputString(String.format("현재 이름 : %s -> 변경할 이름 : ", m.name));
    String currentEmail = Prompt.inputString(String.format("현재 이메일 %s -> 변경할 이메일 : ", m.email));
    String currentPassword = Prompt.inputString(String.format("현재 비밀번호 : %s -> 변경할 비밀번호 : ", m.password));
    String currentPhoto = Prompt.inputString(String.format("현재 사진 : %s -> 변경할 사진 : ", m.photo));
    String currentTel = Prompt.inputString(String.format("현재 전화번호 : %s -> 변경할 전화번호 : ", m.tel));

    m.name = currentName;
    m.email = currentEmail;
    m.password = currentPassword;
    m.photo = currentPhoto;
    m.tel = currentTel;
  }

  public void delete() {
    System.out.println("[멤버 삭제]");
    int num = Prompt.inputInt("번호 : ");

    int i = indexOf(num);
    if(i == -1) {
      System.out.println("삭제할 번호가 존재하지 않습니다.");
    }

    for(int j = i+1; j < this.size; j++) {
      this.members[j-1] = this.members[j];
    }
    System.out.println("삭제 완료");
    this.members[--this.size]= null; 
  }

  int indexOf(int memberNum) {
    for(int i = 0; i < this.size; i++) {
      Member m = this.members[i];
      if(memberNum == m.no) {
        return i;
      }
    }
    return -1;
  }

  Member findByNo(int memberNo) {
    int i = indexOf(memberNo);
    if(i == -1) 
      return null;
    else 
      return this.members[i];
  }

}







