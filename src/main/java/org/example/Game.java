package org.example;

import java.util.*;

public class Game {
    private int stage = 1;
    private boolean isRunning = true;
    private Player player;
    private static Monster monster;

    public Game() {
        this.player = new Player("라이언", 160, 15);
    }

    public void start() {
        System.out.println("Game Start!!");
        System.out.println("기본 아이템을 지급합니다...");
        player.addItem(new ConsumableItem("회복 포션 (40%)", "체력 40% 회복", (int) (player.hp * 0.4)));
        player.addItem(new ConsumableItem("회복 포션 (40%)", "체력 40% 회복", (int) (player.hp * 0.4)));
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            System.out.println("\n=== 스테이지 " + stage + " ===");
            battle();
            if (stage > 30) {
                System.out.println("Game Clear!!");
                isRunning = false;
            }
        }
        scanner.close();
    }

    private void battle() {

        // 보스 (10층마다 등장)
        if (stage % 10 == 0) {
            monster = new Boss("보스 " + stage, 50, 15, stage, "세게 때리기");
        }
        // 30%로 아이템 획득
        else if (Math.random() < 0.3) {
            selectItem();
            stage++;
            return;
        }
        // 일반 몬스터 스테이지
        else {
            monster = new Monster("몬스터 " + stage, 30, 10, stage);
        }

        System.out.println(monster.name + " 출현! HP: " + monster.hp + " 공격력: " + monster.attack);

        Scanner sc = new Scanner(System.in);
        while (!monster.isDead() && !player.isDead()) {
            System.out.println("---------------------------------------\n");
            System.out.println("1. 공격 | 2. 방어 | 3. 인벤토리 | 4. 도망");
            System.out.print("선택: ");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("숫자를 입력하세요.");
                continue;
            }

            int choice = sc.nextInt();

            if (choice < 1 || choice > 4) {
                System.out.println("1~4의 숫자를 입력하세요");
                continue;
            }
            switch (choice) {
                case 1:
                    player.attack(monster);
                    break;
                case 2:
                    player.defend(monster);
                    break;
                case 3:
                    boolean itemuse = player.useInventory();
                    if(!itemuse) continue;
                    break;
                case 4:
                    if (player.escape()) {
                        stage++;
                        if(stage/10 == 0){
                            System.out.println("보스 기운이 발을 묶습니다. 도망칠 수 없습니다.");
                            continue;
                        }
                        return;
                    }
                    break;
            }
            if (!monster.isDead()) {
                monster.attack(player);
            }
        }
        // 전투 결과 처리
        if (player.isDead()) {
            System.out.println("Game over!");
            isRunning = false;
        } else {
            System.out.println("스테이지 " + stage + " 클리어!");
            player.hp += 45; // 스테이지 클리어 후 체력 회복
            player.attack += 5;
            stage++;
        }
    }

    private void selectItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n아이템 선택 스테이지! 원하는 아이템을 선택하세요");
        System.out.println("1. 5 스테이지 동안 공격력 +7");
        System.out.println("2. 체력 40% 회복 포션");
        System.out.println("3. 체력 70% 회복 포션(확률: 70%)");
        System.out.println("4. 상대 몬스터의 HP 절반으로 감소(확률 40%)");

        int number;
        while (true) {
            try {
                System.out.print("아이템 번호를 선택하세요: ");
                if (!sc.hasNextInt()) {
                    sc.next();
                    throw new IllegalArgumentException("숫자를 입력하세요!");
                }
                number = sc.nextInt();

                if (number < 1 || number > 4) {
                    throw new IllegalArgumentException("1~4 사이의 숫자를 입력하세요!");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Item select = switch (number) {
            case 1 -> new BattleItem("강화 포션", "5 스테이지 동안 공격력 7 증가", 7);
            case 2 -> new ConsumableItem("회복 포션 (40%)", "체력 40% 회복", (int) (player.hp * 0.4));
            case 3 -> new ConsumableItem("회복 포션 (70%)", "체력 70% 회복", (int) (player.hp * 0.7));
            case 4 -> new BattleItem("폭탄", "몬스터의 HP를 절반으로 감소");
            default -> null;
        };

        if(number == 3){//30프로 확률로 실패
            if(Math.random() < 0.3){
                System.out.println("획득 실패!");
                return;
            }
        }
        if(number == 4){//60프로 확률로 실패
            if(Math.random() < 0.6){
                System.out.println("획득 실패!");
                return;
            }
        }

        player.addItem(select);
        System.out.println(select.getName() + " 획득!");
    }

    public static Monster getMonster(){
        return monster;
    }
}

