package org.example;


public class Monster extends Character {
    public Monster(String name, int hp, int attack, int stage) {
        super(name, hp, attack);
        scaleStats(stage);
    }

    public void scaleStats(int stage) {
        try {
            if (stage < 1)
                throw new IllegalArgumentException("스테이지는 1 이상이어야 합니다!");
            hp += stage * 12;
            attack += stage * 3;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void attack(Character target) {
        try {
            if (target.isDead()) throw new IllegalStateException("플레이어에게 공격할 수 없습니다!");
            System.out.println(name + "가 " + target.name + "를 공격합니다!");
            target.takeDamage(attack);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setHp(int health) {
        this.hp = health;
    }

    public int getHp(){
        return this.hp;
    }
}
