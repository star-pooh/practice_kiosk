package new_level2;

public class Main {
  public static void main(String[] args) {
    // 키오스크 객체 생성 및 프로그램 시작
    Kiosk kiosk = new Kiosk(new Menu());
    kiosk.start();
  }
}
