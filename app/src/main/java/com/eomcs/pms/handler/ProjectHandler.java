package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectHandler {

  static final int LENGTH = 100;
  MemberHandler memberList;
  Project[] projects = new Project[LENGTH];
  int size = 0;
  public ProjectHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }


  public void add() {
    System.out.println("[프로젝트 등록]");

    Project p = new Project();
    p.no = Prompt.inputInt("번호? ");
    p.title = Prompt.inputString("프로젝트명? ");
    p.content = Prompt.inputString("내용? ");
    p.startDate = Prompt.inputDate("시작일? ");
    p.endDate = Prompt.inputDate("종료일? ");

    while (true) {
      String name = Prompt.inputString("만든이?(취소: 빈 문자열) ");
      if (name.length() == 0) {
        System.out.println("프로젝트 등록을 취소합니다.");
        return;
      } 
      if (this.memberList.exist(name)) {
        p.owner = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

    p.members = "";
    while (true) {
      String name = Prompt.inputString("팀원?(완료: 빈 문자열) ");
      if (name.length() == 0) {
        break;
      } else if (this.memberList.exist(name)) {
        if (!p.members.isEmpty()) {
          p.members += ",";
        }
        p.members += name;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }

    this.projects[this.size++] = p;
  }

  public void list() {
    System.out.println("[프로젝트 목록]");

    for (int i = 0; i < size; i++) {
      Project p = projects[i];
      if(this.projects[i] != null) continue;
      System.out.printf("%d, %s, %s, %s, %s, [%s]\n",
          p.no, p.title, p.startDate, p.endDate, p.owner, p.members);
    }
  }


  public void update() {
    System.out.println("[프로젝트 수정]");
    int num = Prompt.inputInt("번호 : ");

    Project p = findByNo(num);
    if(p == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }


    String currentTitle = Prompt.inputString(String.format("현재 프로젝트명 : %s -> 변경할 프로젝트명 : ", p.title));
    String currentContent = Prompt.inputString(String.format("현재 내용 : %s -> 변경할 내용 : ", p.content));
    Date currentStartDate = Prompt.inputDate(String.format("현재 시작일 : %s -> 변경할 시작일 : ", p.startDate));
    Date currentEndDate = Prompt.inputDate(String.format("현재 종료일 : %s -> 변경할 종료일 : ", p.endDate));
    while(true) {
      String currentOwner = Prompt.inputString(String.format("현재 만든이 : %s -> 변경할 만든이 : ", p.owner));
      if (this.memberList.exist(currentOwner)) {
        p.owner = currentOwner;
        break;
      }
      else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }
    int a = 1;
    while(true) {

      String currentMembers = Prompt.inputString(String.format("현재 팀원 : %s -> 변경할 팀원 : ", p.members));
      if(a == 1) p.members = "";

      a = 0;
      if (currentMembers.length() == 0) {
        break;
      } 
      else if (this.memberList.exist(currentMembers)) {
        if (!p.members.isEmpty()) {
          p.members += ",";
        }
        p.members += currentMembers;
      }
      else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }

    p.title = currentTitle;
    p.content = currentContent;
    p.startDate = currentStartDate;
    p.endDate = currentEndDate;

  }


  public void delete() {
    System.out.println("[프로젝트 삭제]");
    int num = Prompt.inputInt("번호 : ");

    int i = indexOf(num);
    if(i == -1) {
      System.out.println("삭제할 번호가 없습니다.");
      return;
    }

    for(int j = i+1; j < this.size; j++) {
      this.projects[j-1] = this.projects[j];
    }
    this.projects[--this.size] = null;
    System.out.println("삭제 완료");
  }


  int indexOf(int projectNum) {
    for(int i = 0; i < this.size; i++) {
      Project p = this.projects[i];
      if(p.no == projectNum) {
        return i;
      }
    }
    return -1;
  }

  Project findByNo(int projectNo) {
    int i = indexOf(projectNo);
    if(i == -1) 
      return null;
    else 
      return this.projects[i];
  }

}








