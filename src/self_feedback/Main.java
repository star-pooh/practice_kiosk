package self_feedback;

import self_feedback.dto.Menu;
import self_feedback.service.CartService;
import self_feedback.service.KioskService;
import self_feedback.util.DisplayConsole;

public class Main {
  public static void main(String[] args) {
    // 메뉴 객체 생성
    Menu menu = new Menu();
    // 콘솔 입출력 객체 생성
    DisplayConsole dc = new DisplayConsole();
    // 카트 관리 객체 생성
    CartService sc = new CartService();

    // 키오스크 객체 생성 및 필요 객체 주입
    KioskService kiosk = new KioskService(menu, dc, sc);
    // 프로그램 시작
    kiosk.start();
  }
}
