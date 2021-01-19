package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static Scanner keyboardScan = new Scanner(System.in);
  static final int MEMBER_LENGTH = 100;
  static final int PROJECT_LENGTH = 100;
  static final int TASK_LENGTH = 100;

  static int[] no = new int[MEMBER_LENGTH];
  static String[] name = new String[MEMBER_LENGTH];
  static String[] email = new String[MEMBER_LENGTH];
  static String[] password = new String[MEMBER_LENGTH];
  static String[] photo = new String[MEMBER_LENGTH];
  static String[] tel = new String[MEMBER_LENGTH];
  static Date[] registeredDate = new Date[MEMBER_LENGTH];

  static int[] pNo = new int[PROJECT_LENGTH];
  static String[] pTitle = new String[PROJECT_LENGTH];
  static String[] pContent = new String[PROJECT_LENGTH];
  static Date[] pStartDate = new Date[PROJECT_LENGTH];
  static Date[] pEndDate = new Date[PROJECT_LENGTH];
  static String[] pOwner = new String[PROJECT_LENGTH];
  static String[] pMembers = new String[PROJECT_LENGTH];

  static int[] tNo = new int[TASK_LENGTH];
  static String[] tContent = new String[TASK_LENGTH];
  static Date[] tDeadline = new Date[TASK_LENGTH];
  static String[] tOwner = new String[TASK_LENGTH];
  static int[] tStatus = new int[TASK_LENGTH];

  static int mCount = 0;
  static int pCount = 0;
  static int tCount = 0;

  static String response;
  static String input;

  static void addMember() {
    System.out.println("[멤버 등록]");
    for (int i = mCount; i < MEMBER_LENGTH; i++) {
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyboardScan.nextLine());

      System.out.print("이름? ");
      name[i] = keyboardScan.nextLine();

      System.out.print("이메일? ");
      email[i] = keyboardScan.nextLine();

      System.out.print("암호? ");
      password[i] = keyboardScan.nextLine();

      System.out.print("사진? ");
      photo[i] = keyboardScan.nextLine();

      System.out.print("전화? ");
      tel[i] = keyboardScan.nextLine();

      registeredDate[i] = new java.sql.Date(System.currentTimeMillis());

      mCount++;

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(y/N) ");
      response = keyboardScan.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
      System.out.println(); 
    }
  }

  static void listMember() {
    System.out.println("[멤버 목록]");
    for (int i = 0; i < mCount; i++) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          no[i], name[i], email[i], tel[i], registeredDate[i]);
    }
  }

  static void addProject() {
    System.out.println("[프로젝트 등록]");
    for (int i = pCount; i < PROJECT_LENGTH; i++) {
      System.out.print("번호? ");
      pNo[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("프로젝트명? ");
      pTitle[i] = keyboardScan.nextLine();

      System.out.print("내용? ");
      pContent[i] = keyboardScan.nextLine();

      System.out.print("시작일? ");
      pStartDate[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("종료일? ");
      pEndDate[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("만든이? ");
      pOwner[i] = keyboardScan.nextLine();

      System.out.print("팀원? ");
      pMembers[i] = keyboardScan.nextLine();

      pCount++;
      System.out.println();

      System.out.print("계속 입력하시겠습니까?(y/N) ");
      response = keyboardScan.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
      System.out.println();
    }
  }

  static void listProject() {
    System.out.println("[프로젝트 목록]");
    for (int i = 0; i < pCount; i++) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          pNo[i], pTitle[i], pStartDate[i], pEndDate[i], pOwner[i]);
    }
  }

  static void addTask() {
    System.out.println("[작업 등록]");
    for (int i = tCount; i < TASK_LENGTH; i++) {
      System.out.print("번호? ");
      tNo[i] = Integer.parseInt(keyboardScan.nextLine());

      System.out.print("내용? ");
      tContent[i] = keyboardScan.nextLine();

      System.out.print("마감일? ");
      tDeadline[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.println("상태?");
      System.out.println("0: 신규");
      System.out.println("1: 진행중");
      System.out.println("2: 완료");
      System.out.print("> ");
      tStatus[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("담당자? ");
      tOwner[i] = keyboardScan.nextLine();

      tCount++;
      System.out.println();

      System.out.print("계속 입력하시겠습니까?(y/N) ");
      response = keyboardScan.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
      System.out.println();
    }
  }

  static void listTask() {
    System.out.println("[작업 목록]");
    for (int i = 0; i < tCount; i++) {
      String stateLabel = null;
      switch (tStatus[i]) {
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
          tNo[i], tContent[i], tDeadline[i], stateLabel, tOwner[i]);
    }
  }

  public static void main(String[] args) {

    while(true) {
      System.out.print("명령 > ");
      input = keyboardScan.nextLine();
      if(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
        break;
      }
      else if (input.equalsIgnoreCase("/member/add")) {
        addMember();
      }
      else if (input.equalsIgnoreCase("/member/list")) {
        listMember();
      }
      else if (input.equalsIgnoreCase("/project/add")) {
        addProject();
      }
      else if (input.equalsIgnoreCase("/project/list")) {
        listProject();
      }
      else if (input.equalsIgnoreCase("/task/add")) {
        addTask();
      }
      else if (input.equalsIgnoreCase("/task/list")) {
        listTask();
      }
      else {
        System.out.println("실행할 수 없는 명령어입니다.");
      }

      System.out.println();
    }




    keyboardScan.close();

    System.out.println("--------------------------------");


  }
}
