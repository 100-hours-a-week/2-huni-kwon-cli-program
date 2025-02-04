package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        printTitleScreen();

        Game game = new Game();

        while(true) {
            System.out.println("1. 시작");
            System.out.println("2. 종료");
            System.out.print("입력: ");

            // 사용자 입력 받기
            Scanner sc = new Scanner(System.in);

            if (!sc.hasNextInt()) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                sc.next();
                continue;
            }
            int input = sc.nextInt();

            if(input == 1){
                game.start();
                break;
            } else if (input == 2) {
                System.out.println("으악.");
                System.exit(0);
                break;
            }else System.out.println("잘못된 입력입니다.");
        }
    }

    // 타이틀 화면 출력 메소드
    private static void printTitleScreen() {
        System.out.println("=================================");
        System.out.println("            Welcome!           ");
        System.out.println("              KTB              ");
        System.out.println("           -roguelike-          ");
        System.out.println("=================================");
        System.out.println("                .--:                       ");
        System.out.println("               =#++**.                     ");
        System.out.println("              :%*+*+%+.                      ");
        System.out.println("           :=****+++***+-.                  ");
        System.out.println("         :**+==========+**-               ");
        System.out.println("    :=+=+#+===============#+.                ");
        System.out.println("   -#+*%*=============*====**.                ");
        System.out.println("  .#==%+===========+#%*=====*+                ");
        System.out.println("  .*+#+===========*#+=======-#-              ");
        System.out.println("   :##==============-#%===+*##*               ");
        System.out.println("    *+======*+========+==#%@@@%.                ");
        System.out.println("   .#====+#%#+======++=-+@%@@@@-                  ");
        System.out.println("   .#====#*=++====#%+=**#@@@@%@=                  ");
        System.out.println("    *+======#%==+##*  -%=+%%%%%*+=:.              ");
        System.out.println("    -#==========#. :*+#+====+%===+**+-.           ");
        System.out.println("    .*+-+*#*+===#=-#*+======#+======+**+-.        ");
        System.out.println("     .#*#+=+#*==%**=======+#**====+====+**=.      ");
        System.out.println("      :%+====%=++========*#==#====#+======*#-     ");
        System.out.println("      .#=====%========+*#*=:*+====%=========#=    ");
        System.out.println("      .#=====%+===++*##+=-.:#====+#:=========#.   ");
        System.out.println("      .#+====####***+===:..#+====#-.=##+=====#:   ");

    }
}
