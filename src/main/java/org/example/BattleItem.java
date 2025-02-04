package org.example;

class BattleItem extends Item {
    private int damageBoost;
    private boolean boom;

    //도핑
    public BattleItem(String name, String description, int damageBoost) {
        super(name, description);
        this.damageBoost = damageBoost;
        boom = false;
    }

    //폭탄
    public BattleItem(String name, String description) {
        super(name, description);
        boom = true;
    }

    @Override
    public void use(Player player) {
        if(boom){
            Monster monster = player.getMonster();
            monster.setHp(monster.getHp()/2);
        }
    }
}
