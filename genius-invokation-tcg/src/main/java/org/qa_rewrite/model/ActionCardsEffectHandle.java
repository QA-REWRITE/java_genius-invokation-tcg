package org.qa_rewrite.model;

/**行动牌效果
 * @author QA
 */
public class ActionCardsEffectHandle implements StaticValue{
    /**装备牌效果*/

    /**
     * 重帘留香
     * 技能说明：
     * 战斗行动：我方出战角色为行秋时，装备此牌。
     * 行秋装备此牌后，立刻使用一次画雨笼山。
     * 装备有此牌的行秋生成的雨帘剑，初始可用次数+1。
     * （牌组中包含行秋，才能加入牌组）
     */
    public static void theScentRemained(LeaderType leaderType){
        if(leaderType.equals(LeaderType.LEADER_PLAYER)){
            /*设置行动类型为战斗行动*/
            GameValue.player.setActionType(ActionType.ACTION_COMBAT);
            CharacterCardsEffectHandle.charXingQiuE(leaderType);
        }else {
            /*设置行动类型为战斗行动*/
            GameValue.opponent.setActionType(ActionType.ACTION_COMBAT);
            CharacterCardsEffectHandle.charXingQiuE(leaderType);
        }
    }

    /**
     * 唯此一心
     * 技能说明：
     * 战斗行动：我方出战角色为甘雨时，装备此牌。
     * 甘雨装备此牌后，立刻使用一次霜华矢。
     * 装备有此牌的甘雨使用霜华矢时：如果此技能在本场对局中曾经被使用过，则其造成的卡牌冰元素伤害+1，并且改为对敌方后台角色造成3点穿透伤害。
     * （牌组中包含甘雨，才能加入牌组）
     */
    public static void undividedHeart(){

    }

}
