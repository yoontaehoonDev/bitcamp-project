package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    Scanner keyboardScan = new Scanner(System.in);

    // 최대 입력 개수
    final int MEMBER_LENGTH = 100;
    final int PROJECT_LENGTH = 100;
    final int TASK_LENGTH = 100;

    int[] no = new int[MEMBER_LENGTH];
    String[] name = new String[MEMBER_LENGTH];
    String[] email = new String[MEMBER_LENGTH];
    String[] password = new String[MEMBER_LENGTH];
    String[] photo = new String[MEMBER_LENGTH];
    String[] tel = new String[MEMBER_LENGTH];
    Date[] registeredDate = new Date[MEMBER_LENGTH];

    int[] pNo = new int[PROJECT_LENGTH];
    String[] pTitle = new String[PROJECT_LENGTH];
    String[] pContent = new String[PROJECT_LENGTH];
    Date[] pStartDate = new Date[PROJECT_LENGTH];
    Date[] pEndDate = new Date[PROJECT_LENGTH];
    String[] pOwner = new String[PROJECT_LENGTH];
    String[] pMembers = new String[PROJECT_LENGTH];

    int[] tNo = new int[TASK_LENGTH];
    String[] tContent = new String[TASK_LENGTH];
    Date[] tDeadline = new Date[TASK_LENGTH];
    String[] tOwner = new String[TASK_LENGTH];
    int[] tStatus = new int[TASK_LENGTH];

    int mCount = 0;
    int pCount = 0;
    int tCount = 0;

    String response;

    while(true) {
      System.out.print("명령 > ");
      String input = keyboardScan.nextLine();
      if(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
        break;
      }
      else if (input.equalsIgnoreCase("/member/add")) {
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
      else if (input.equalsIgnoreCase("/member/list")) {
        System.out.println("[멤버 목록]");
        for (int i = 0; i < mCount; i++) {
          System.out.printf("%d, %s, %s, %s, %s\n",
              no[i], name[i], email[i], tel[i], registeredDate[i]);
        }
      }
      else if (input.equalsIgnoreCase("/project/add")) {
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
      else if (input.equalsIgnoreCase("/project/list")) {
        System.out.println("[프로젝트 목록]");
        for (int i = 0; i < pCount; i++) {
          System.out.printf("%d, %s, %s, %s, %s\n",
              pNo[i], pTitle[i], pStartDate[i], pEndDate[i], pOwner[i]);
        }
      }
      else if (input.equalsIgnoreCase("/task/add")) {
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
      else if (input.equalsIgnoreCase("/task/list")) {
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
      else {
        System.out.println("실행할 수 없는 명령어입니다.");
      }

      System.out.println();
    }




    keyboardScan.close();

    System.out.println("--------------------------------");


  }
}
