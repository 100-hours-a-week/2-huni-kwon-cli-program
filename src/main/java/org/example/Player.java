package org.example;

import java.util.Scanner;

class Player extends Character {
    private Inventory inventory;

    public Player(String name, int hp, int attack) {
        super(name, hp, attack);
        this.inventory = new Inventory();
    }

    public void defend(Monster monster) {
        try {
            System.out.println(name + "가 방어를 시도합니다!");
            if (Math.random() < 0.2) {
                int reducedDamage = monster.attack / 2;
                takeDamage(reducedDamage);
                System.out.println("방어 성공! 피해 감소: " + reducedDamage);
            } else {
                takeDamage(monster.attack);
                System.out.println("방어 실패!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean useInventory() {
        try {
            inventory.showInventory();
            boolean useItem = inventory.useItem(this);
            if(!useItem) return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public void addItem(Item item) {
        try {
            if (item == null) {
                throw new IllegalArgumentException("추가할 아이템이 없습니다!");
            }
            inventory.addItem(item);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean escape() {
        try {
            boolean success = Math.random() < 0.6;
            if (success) {
                System.out.println("도망 성공!");
                return true;
            } else {
                throw new Exception("도망 실패! 전투를 진행합니다.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Monster getMonster(){
        return Game.getMonster();
    }
}
