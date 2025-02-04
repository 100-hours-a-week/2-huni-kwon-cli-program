package org.example;

class ConsumableItem extends Item {
    private int heal;

    public ConsumableItem(String name, String description, int heal) {
        super(name, description);
        this.heal = heal;
    }

    @Override
    public void use(Player player) {
        try {
            if (player == null) throw new IllegalArgumentException("플레이어가 존재하지 않습니다!");
            System.out.println("🩹 " + player.name + "가 " + name + "를 사용하여 체력을 " + heal + " 회복했습니다!");
            player.hp += heal;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
