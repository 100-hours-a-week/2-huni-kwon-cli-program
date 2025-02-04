package org.example;

import java.util.ArrayList;
import java.util.*;

public class Inventory {
    private List<Item> items;
    Scanner sc = new Scanner(System.in);

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        try {
            if (item == null) throw new IllegalArgumentException("추가할 아이템이 없습니다!");
            items.add(item);
            System.out.println(item.getName() + "를 인벤토리에 추가했습니다");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean useItem(Player player) {
        try {
            while (true) {
                System.out.println("\n사용할 아이템 번호를 선택하세요(0 입력 시 취소): ");

                if (!sc.hasNextInt()) {
                    sc.next(); // 잘못된 입력 방지
                    System.out.println("숫자를 입력하세요!");
                    continue;
                }

                int choice = sc.nextInt();
                if (choice == 0) {
                    System.out.println("아이템 사용을 취소했습니다.");
                    return false;
                }

                if (choice < 1 || choice > items.size()) {
                    System.out.println("올바른 숫자를 입력하세요!");
                    continue;
                }else{
                    Item select = items.remove(choice - 1); // 선택한 아이템 제거 후 사용
                    select.use(player);
                    System.out.println(select.getName() + "사용");
                    return true;
                }
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void showInventory() {
        try {
            if (items.isEmpty()) throw new IllegalStateException("인벤토리가 비어 있습니다");
            System.out.println("[인벤토리 목록]");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName() + " - " + items.get(i).getDescription());
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
