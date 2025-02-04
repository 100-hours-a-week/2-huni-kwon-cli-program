package org.example;
abstract class Character {
    protected String name;
    protected int hp;
    protected int attack;

    public Character(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public void attack(Character target) {
        try {
            if (hp <= 0)
                throw new IllegalStateException("체력이 0 이하입니다. 공격할 수 없습니다!");
            if (target.isDead())
                throw new IllegalStateException("공격할 수 있는 상대가 없습니다.");
            System.out.println(name + "가 " + target.name + "를 공격합니다!");
            target.takeDamage(attack);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void takeDamage(int damage) {
        try {
            if (damage < 0) throw new IllegalArgumentException("데미지는 음수일 수 없습니다!");
            hp -= damage;
            System.out.println(name + "가 " + damage + " 데미지를 입었습니다. 남은 체력: " + hp);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }
}
