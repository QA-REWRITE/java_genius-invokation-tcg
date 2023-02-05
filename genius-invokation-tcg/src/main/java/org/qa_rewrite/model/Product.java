package org.qa_rewrite.model;

/**生成物
 * @author QA
 */
public class Product implements StaticValue {

    /*元素反应生成物*/

    /**
     * 【燃烧烈焰】
     * 结束阶段：造成1点火元素伤害。（可用次数1，最多叠加到2）
     */
    public static void burningFlamesTimes(LeaderType leaderType) {
        CharacterCard charCard = new CharacterCard();
        charCard.setElementType(ElementType.ELEM_PYRO);
        /*如果发动者是玩家*/
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            AttackHandle.elementAttack(leaderType, charCard, GameValue.opponent.getReceptionChar(), 1);
            int times = GameValue.player.getBurningFlamesTimes();
            GameValue.player.setBurningFlamesTimes(times - 1);
        } else {
            AttackHandle.elementAttack(leaderType, charCard, GameValue.player.getReceptionChar(), 1);
            int times = GameValue.opponent.getBurningFlamesTimes();
            GameValue.opponent.setBurningFlamesTimes(times - 1);
        }

    }

    /**
     * 【草原核】
     * 我方对敌方出战角色造成火元素伤害或雷元素伤害时，伤害值+2。（可用次数1）
     */
    public static void dendroCoreTimes(LeaderType leaderType) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            if (GameValue.player.getDamageType().equals(DamageType.DAMAGE_PYRO) || GameValue.player.getDamageType().equals(DamageType.DAMAGE_ELECTRO)) {
                int damageValue = GameValue.player.getReceptionChar().getMakeDamageValue();
                GameValue.player.getReceptionChar().setMakeDamageValue(damageValue + 2);
                int times = GameValue.player.getDendroCoreTimes();
                GameValue.player.setDendroCoreTimes(times - 1);
            } else {
                int damageValue = GameValue.opponent.getReceptionChar().getMakeDamageValue();
                GameValue.opponent.getReceptionChar().setMakeDamageValue(damageValue + 2);
                int times = GameValue.opponent.getDendroCoreTimes();
                GameValue.opponent.setDendroCoreTimes(times - 1);
            }
        }

    }

    /**
     * 【激化领域】
     * 我方对敌方出战角色造成雷元素伤害或草元素伤害时，伤害值+1。（可用次数2）（3.4将可用次数“3”改为“2”）
     */
    public static void catalyzeAreasTimes(LeaderType leaderType) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            if (GameValue.player.getDamageType().equals(DamageType.DAMAGE_ELECTRO) || GameValue.player.getDamageType().equals(DamageType.DAMAGE_DENDRO)) {
                int damageValue = GameValue.player.getReceptionChar().getMakeDamageValue();
                GameValue.player.getReceptionChar().setMakeDamageValue(damageValue + 1);
                int times = GameValue.player.getCatalyzeAreasTimes();
                GameValue.player.setCatalyzeAreasTimes(times - 1);
            } else {
                int damageValue = GameValue.opponent.getReceptionChar().getMakeDamageValue();
                GameValue.opponent.getReceptionChar().setMakeDamageValue(damageValue + 1);
                int times = GameValue.opponent.getCatalyzeAreasTimes();
                GameValue.opponent.setCatalyzeAreasTimes(times - 1);
            }
        }
    }

    /*角色攻击生成物*/

    /**
     * 甘雨元素战技生成物：冰莲
     * 己方出战角色受到伤害时：抵消1点伤害。
     * 可用次数：2
     */
    public static void iceLotus(LeaderType leaderType) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            int currShielaValue = GameValue.player.getReceptionChar().getCurrentShieldValue();
            GameValue.player.getReceptionChar().setCurrentShieldValue(currShielaValue + 2);
            int times = GameValue.player.getIceLotusTimes();
            GameValue.player.setIceLotusTimes(times - 2);
        } else {
            int currShielaValue = GameValue.opponent.getReceptionChar().getCurrentShieldValue();
            GameValue.opponent.getReceptionChar().setCurrentShieldValue(currShielaValue + 2);
            int times = GameValue.opponent.getIceLotusTimes();
            GameValue.opponent.setIceLotusTimes(times - 2);
        }

    }

    /**
     * 莫娜元素爆发生成物：泡影
     * 我方技能造成伤害时：移除此状态，使本次伤害加倍。
     */
    public static void bubble(LeaderType leaderType) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            if (GameValue.player.isAliveBubble()) {
                int damage = GameValue.player.getReceptionChar().getMakeDamageValue();
                GameValue.player.getReceptionChar().setMakeDamageValue(damage * 2);
                GameValue.player.setAliveBubble(false);
            }
        } else {
            if (GameValue.opponent.isAliveBubble()) {
                int damage = GameValue.opponent.getReceptionChar().getMakeDamageValue();
                GameValue.opponent.getReceptionChar().setMakeDamageValue(damage * 2);
                GameValue.opponent.setAliveBubble(false);
            }
        }
    }

    /**
     * 行秋元素战技生成物：雨帘剑
     * 我方出战角色受到至少为3的伤害时：抵消1点伤害。
     * 可用次数：2
     */
    public static void rainCurtainSword(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.getReceptionChar().getSufferDamageValue()>=3){
                int currHp=GameValue.player.getReceptionChar().getCurrentHpValue();
                GameValue.player.getReceptionChar().setCurrentHpValue(currHp+1);
                int times=GameValue.player.getRainCurtainSwordTimes();
                GameValue.player.setRainCurtainSwordTimes(times-1);
            }else{
                int currHp=GameValue.opponent.getReceptionChar().getCurrentHpValue();
                GameValue.opponent.getReceptionChar().setCurrentHpValue(currHp+1);
                int times=GameValue.opponent.getRainCurtainSwordTimes();
                GameValue.opponent.setRainCurtainSwordTimes(times-1);
            }
        }
    }

    /**
     * 行秋元素爆发生成物：虹剑势
     * 我方角色普通攻击后：造成2点水元素伤害
     * 可用次数：3
     */
    public static void rainbowSwordPotential(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(),2);
            int times=GameValue.player.getRainbowSwordPotentialTimes();
            GameValue.player.setRainbowSwordPotentialTimes(times-1);
        }else{
            AttackHandle.elementAttack(leaderType,GameValue.opponent.getReceptionChar(),GameValue.player.getReceptionChar(),2);
            int times=GameValue.opponent.getRainbowSwordPotentialTimes();
            GameValue.opponent.setRainbowSwordPotentialTimes(times-1);
        }

    }
}
