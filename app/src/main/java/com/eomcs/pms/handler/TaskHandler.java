package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskHandler {

  static final int LENGTH = 100;

  MemberHandler memberList;
  Task[] tasks = new Task[LENGTH];
  int size = 0;

  public TaskHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("[작업 등록]");

    Task t = new Task();
    t.no = Prompt.inputInt("번호? ");
    t.content = Prompt.inputString("내용? ");
    t.deadline = Prompt.inputDate("마감일? ");
    t.status = Prompt.inputInt("상태?\n0: 신규\n1: 진행중\n2: 완료\n> ");

    while (true) {
      String name = Prompt.inputString("담당자?(취소: 빈 문자열) ");
      if (name.length() == 0) {
        System.out.println("작업 등록을 취소합니다.");
        return;
      } else if (this.memberList.exist(name)) {
        t.owner = name;
        break;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }

    this.tasks[this.size++] = t;
  }

  public void list() {
    System.out.println("[작업 목록]");

    for (int i = 0; i < this.size; i++) {
      Task t = this.tasks[i];
      if(this.tasks[i] != null) continue;

      String stateLabel = null;
      switch (t.status) {
        case 1:
          stateLabel = "진행중";
          break;
        case 2:
          stateLabel = "완료";
          break;
        default:
          stateLabel = "신규";
      }
      System.out.printf("%d, %s, %s, %s, %s\n", 
          t.no, t.content, t.deadline, stateLabel, t.owner);
    }
  }

  public void update() {
    System.out.println("[작업 목록 수정]");
    int num = Prompt.inputInt("번호 : ");

    Task t = findByNo(num);
    if(t == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String currentContent = Prompt.inputString(String.format("현재 내용 : %s -> 변경할 내용 : ", t.content));
    Date currentDeadline = Prompt.inputDate(String.format("현재 마감일 : %s -> 변경할 마감일 : ", t.deadline));
    int currentStatus = Prompt.inputInt(String.format("현재 상태 : %s -> 변경할 상태 : ", t.status));

    while (true) {
      String currentName = Prompt.inputString(String.format("현재 담당자 : %s -> 변경할 담당자 : ", t.owner));
      if (currentName.length() == 0) {
        System.out.println("작업 수정을 취소합니다.");
        return;
      } else if (this.memberList.exist(currentName)) {
        t.owner = currentName;
        break;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }

    t.content = currentContent;
    t.deadline = currentDeadline;
    t.status = currentStatus;
  }

  public void delete() {
    System.out.println("[작업 삭제]");
    int num = Prompt.inputInt("번호 : ");

    int i = indexOf(num);
    if(i == -1) {
      System.out.println("삭제할 번호가 없습니다.");
      return;
    }

    for(int j = i+1; j < this.size; j++) {
      this.tasks[j-1] = this.tasks[j];
    }
    this.tasks[--this.size] = null;
    System.out.println("삭제 완료");
  }

  int indexOf(int taskNum) {
    for(int i = 0; i < this.size; i++) {
      Task t = tasks[i];
      if(t.no == taskNum) {
        return i;
      }
    }
    return -1;
  }

  Task findByNo(int projectNo) {
    int i = indexOf(projectNo);
    if(i == -1) 
      return null;
    else 
      return this.tasks[i];
  }
}
