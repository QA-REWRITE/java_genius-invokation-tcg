package org.qa_rewrite.model;

/**
 * 召唤物类
 * @author QA
 */
public class Summoned implements StaticValue{

    /**
     * 甘雨召唤物：冰灵珠
     * 结束阶段：造成1点冰元素伤害，对所有敌方后台角色造成1点穿透伤害。
     * 可用次数：2
     */
    public static void iceSoulPearl(LeaderType leaderType){
        CharacterCard charCard=new CharacterCard();
        charCard.setElementType(StaticValue.ElementType.ELEM_CRYO);
        /*如果发动者是玩家*/
        if (leaderType.equals(StaticValue.LeaderType.LEADER_PLAYER)){
            AttackHandle.elementAttack(leaderType,charCard,GameValue.opponent.getReceptionChar(), 1);
           for(int i=0;i<3;i++){
               if(!GameValue.opponent.getCharacterCardsList().get(i).equals(GameValue.opponent.getReceptionChar()))
               {
                   int curHp=GameValue.opponent.getCharacterCardsList().get(i).getCurrentHpValue();
                   GameValue.opponent.getCharacterCardsList().get(i).setCurrentHpValue(curHp-1);
               }
           }
            int times=GameValue.player.getIceSoulPearlTimes();
            GameValue.player.setIceSoulPearlTimes(times-1);
        }else{
            AttackHandle.elementAttack(leaderType,charCard,GameValue.player.getReceptionChar(), 1);
            for(int i=0;i<3;i++){
                if(!GameValue.player.getCharacterCardsList().get(i).equals(GameValue.player.getReceptionChar()))
                {
                    int curHp=GameValue.player.getCharacterCardsList().get(i).getCurrentHpValue();
                    GameValue.player.getCharacterCardsList().get(i).setCurrentHpValue(curHp-1);
                }
            }
            int times=GameValue.opponent.getBurningFlamesTimes();
            GameValue.opponent.setBurningFlamesTimes(times-1);
        }
    }

    /**
     * 莫娜召唤物：虚影
     * 我方出战角色受到伤害时：抵消1点伤害。
     * 可用次数：1，耗尽时不弃置此牌
     * 结束阶段：弃置此牌，造成1点水元素伤害
     */
    public static void virtualShadow(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            int currShielaValue=GameValue.player.getReceptionChar().getCurrentShieldValue();
            GameValue.player.getReceptionChar().setCurrentShieldValue(currShielaValue+2);
            int times=GameValue.player.getIceLotusTimes();
            GameValue.player.setIceLotusTimes(times-1);
            /*如果弃置这个召唤物*/
            if (GameValue.player.isAbandonVirtualShadow()){
                CharacterCard charCard=new CharacterCard();
                charCard.setElementType(ElementType.ELEM_HYDRO);
                AttackHandle.elementAttack(leaderType,charCard,GameValue.opponent.getReceptionChar(),1);
            }


        }else{
            int currShielaValue=GameValue.opponent.getReceptionChar().getCurrentShieldValue();
            GameValue.opponent.getReceptionChar().setCurrentShieldValue(currShielaValue+2);
            int times=GameValue.opponent.getIceLotusTimes();
            GameValue.opponent.setIceLotusTimes(times-1);

            /*如果弃置这个召唤物*/
            if (GameValue.opponent.isAbandonVirtualShadow()){
                CharacterCard charCard=new CharacterCard();
                charCard.setElementType(ElementType.ELEM_HYDRO);
                AttackHandle.elementAttack(leaderType,charCard,GameValue.player.getReceptionChar(),1);
            }
        }

    }

}
