package org.example;

class Boss extends Monster {
    private String Skill;

    public Boss(String name, int hp, int attack, int stage, String specialSkill) {
        super(name, (int) (hp * 1.5), (int)(attack * 1.2), stage);
        this.Skill = specialSkill;
    }

    @Override
    public void attack(Character target) {
        try {
            if (target.isDead()) throw new IllegalStateException("플레이어에게 공격할 수 없습니다!");

            if (Math.random() < 0.3) {  // 1/3 확률로 specialattack
                useSkill(target);
            } else {
                System.out.println(name + "의 공격!");
                target.takeDamage(attack);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void useSkill(Character target) {
        System.out.println(name + "가 " + Skill + "를 사용했습니다!");
        target.takeDamage(attack * 2);
    }
}
