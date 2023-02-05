package org.qa_rewrite.model;


/**角色牌效果处理方法
 * @author QA
 */
public class CharacterCardsEffectHandle implements StaticValue {
    /**角色牌效果
     *角色牌：甘雨
     * 普通攻击1
     * 流天射术
     * 普通攻击
     * 造成2点物理伤害。
     */
    public static void charGanYuA1(LeaderType leaderType) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            /*造成2点物理伤害*/
            if (GameValue.player.isAvailableA1()) {
                AttackHandle.physicsAttack(GameValue.opponent.getReceptionChar(), 2);
                /*更新元素骰点数与种类*/
                int cryo = GameValue.dicesOfPlayer.getCryoDices();
                int count = GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setCryoDices(cryo - 1);
                GameValue.dicesOfPlayer.setDicesCount(count - 3);
                /*普通攻击、元素战技都能增加1点充能*/
                int charge=GameValue.player.getReceptionChar().getChargeValue();
                GameValue.player.getReceptionChar().setChargeValue(charge+1);
            }
        }else{
            if (GameValue.opponent.isAvailableA1())
            {
                AttackHandle.physicsAttack(GameValue.player.getReceptionChar(), 2);
                /*更新元素骰点数与种类*/
                int cryo = GameValue.dicesOfOpponent.getCryoDices();
                int count = GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setCryoDices(cryo - 1);
                GameValue.dicesOfOpponent.setDicesCount(count - 3);
                /*普通攻击、元素战技都能增加1点充能*/
                int charge=GameValue.opponent.getReceptionChar().getChargeValue();
                GameValue.opponent.getReceptionChar().setChargeValue(charge+1);
            }

        }
    }
    /**普通攻击2
     *霜华矢
     * 普通攻击
     * 造成2点冰元素伤害，对所有敌方后台角色造成2点穿透伤害。
     * */
    public static void charGanYuA2(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.isAvailableA2()){
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(),2);
                /*穿透伤害*/
                for(int i=0;i<3;i++){
                    if (!GameValue.opponent.getCharacterCardsList().get(i).equals(GameValue.opponent.getReceptionChar())){
                        int currHp=GameValue.opponent.getCharacterCardsList().get(i).getCurrentHpValue();
                        int currShield=GameValue.opponent.getCharacterCardsList().get(i).getCurrentShieldValue();
                        GameValue.opponent.getCharacterCardsList().get(i).setCurrentHpValue(currHp+currShield-2);
                    }
                }

                int cryo=GameValue.dicesOfPlayer.getCryoDices();
                GameValue.dicesOfPlayer.setCryoDices(cryo-5);
                int count=GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setDicesCount(count-5);
                /*普通攻击、元素战技都能增加1点充能*/
                int charge=GameValue.player.getReceptionChar().getChargeValue();
                GameValue.player.getReceptionChar().setChargeValue(charge+1);
            }
        }else{
            if (GameValue.opponent.isAvailableA2()){
                AttackHandle.elementAttack(leaderType,GameValue.opponent.getReceptionChar(),GameValue.player.getReceptionChar(),2);
                /*穿透伤害*/
                for(int i=0;i<3;i++){
                    if (!GameValue.player.getCharacterCardsList().get(i).equals(GameValue.player.getReceptionChar())){
                        int currHp=GameValue.player.getCharacterCardsList().get(i).getCurrentHpValue();
                        int currShield=GameValue.player.getCharacterCardsList().get(i).getCurrentShieldValue();
                        GameValue.player.getCharacterCardsList().get(i).setCurrentHpValue(currHp+currShield-2);
                    }
                }

                int cryo=GameValue.dicesOfOpponent.getCryoDices();
                GameValue.dicesOfOpponent.setCryoDices(cryo-5);
                int count=GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setDicesCount(count-5);
                /*普通攻击、元素战技都能增加1点充能*/
                int charge=GameValue.opponent.getReceptionChar().getChargeValue();
                GameValue.opponent.getReceptionChar().setChargeValue(charge+1);
            }
        }

    }

    /**攻击E
     * 山泽麟迹
     * 元素战技
     * 造成1点冰元素伤害，生成冰莲。
     * (冰莲：
     * 我方出战角色受到伤害时：抵消1点伤害。
     * 可用次数：2）
     */
    public static void charGanYuE(LeaderType leaderType) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            if (GameValue.player.isAvailableE()) {
                AttackHandle.elementAttack(leaderType, GameValue.player.getReceptionChar(), GameValue.opponent.getReceptionChar(), 1);
                int times = GameValue.player.getIceLotusTimes();
                GameValue.player.setIceLotusTimes(times + 2);

                int cryo = GameValue.dicesOfPlayer.getCryoDices();
                GameValue.dicesOfPlayer.setCryoDices(cryo - 3);
                int count = GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setDicesCount(count - 3);
                /*普通攻击、元素战技都能增加1点充能*/
                int charge = GameValue.player.getReceptionChar().getChargeValue();
                GameValue.player.getReceptionChar().setChargeValue(charge + 1);
            }
        } else {
            if (GameValue.opponent.isAvailableE()) {
                AttackHandle.elementAttack(leaderType, GameValue.opponent.getReceptionChar(), GameValue.player.getReceptionChar(), 1);
                int times = GameValue.opponent.getIceLotusTimes();
                GameValue.opponent.setIceLotusTimes(times + 2);

                int cryo = GameValue.dicesOfOpponent.getCryoDices();
                GameValue.dicesOfOpponent.setCryoDices(cryo - 3);
                int count = GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setDicesCount(count - 3);
                /*普通攻击、元素战技都能增加1点充能*/
                int charge = GameValue.opponent.getReceptionChar().getChargeValue();
                GameValue.opponent.getReceptionChar().setChargeValue(charge + 1);

            }
        }
    }


    /**攻击Q
     * 降众天华
     * 元素爆发
     * 造成1点冰元素伤害，对所有敌方后台角色造成1点穿透伤害，召唤冰灵珠。
     */
    public static void charGanYuQ(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.isAvailableQ()){
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(),1);
                for (int i=0;i<3;i++){
                    if (!GameValue.opponent.getCharacterCardsList().get(i).equals(GameValue.opponent.getReceptionChar())){
                        int currHp=GameValue.opponent.getCharacterCardsList().get(i).getCurrentHpValue();
                        int currShield=GameValue.opponent.getCharacterCardsList().get(i).getCurrentShieldValue();
                        if (currShield-1>0) {
                            currShield-=1;
                        }else {
                            currShield=0;
                            currHp=currHp+currShield-1;
                        }
                        GameValue.opponent.getCharacterCardsList().get(i).setCurrentShieldValue(currShield);
                        GameValue.opponent.getCharacterCardsList().get(i).setCurrentHpValue(currHp);

                        int cryo=GameValue.dicesOfPlayer.getCryoDices();
                        int count=GameValue.dicesOfPlayer.getDicesCount();
                        int charge=GameValue.player.getReceptionChar().getCurrentChargeValue();
                        GameValue.dicesOfPlayer.setCryoDices(cryo-3);
                        GameValue.dicesOfPlayer.setDicesCount(count-3);
                        GameValue.player.getReceptionChar().setCurrentChargeValue(0);
                    }
                }
            }
        }else {
            if (GameValue.opponent.isAvailableQ()){
                AttackHandle.elementAttack(leaderType,GameValue.opponent.getReceptionChar(),GameValue.player.getReceptionChar(),1);
                for (int i=0;i<3;i++){
                    if (!GameValue.player.getCharacterCardsList().get(i).equals(GameValue.player.getReceptionChar())){
                        int currHp=GameValue.player.getCharacterCardsList().get(i).getCurrentHpValue();
                        int currShield=GameValue.player.getCharacterCardsList().get(i).getCurrentShieldValue();
                        if (currShield-1>0) {
                            currShield-=1;
                        }else {
                            currShield=0;
                            currHp=currHp+currShield-1;
                        }
                        GameValue.player.getCharacterCardsList().get(i).setCurrentShieldValue(currShield);
                        GameValue.player.getCharacterCardsList().get(i).setCurrentHpValue(currHp);

                        int cryo=GameValue.dicesOfOpponent.getCryoDices();
                        int count=GameValue.dicesOfOpponent.getDicesCount();
                        int charge=GameValue.opponent.getReceptionChar().getCurrentChargeValue();
                        GameValue.dicesOfOpponent.setCryoDices(cryo-3);
                        GameValue.dicesOfOpponent.setDicesCount(count-3);
                        GameValue.opponent.getReceptionChar().setCurrentChargeValue(0);
                    }
                }
            }
        }
    }

    /**莫娜
     * 因果点破
     * 普通攻击
     * 造成1点水元素伤害。
     */
    public static void charMonaA(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.isAvailableA1()){
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(), 1);

                int hydro=GameValue.dicesOfPlayer.getCryoDices();
                int count=GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setHydroDices(hydro-1);
                GameValue.dicesOfPlayer.setDicesCount(count-3);

                int charge=GameValue.player.getReceptionChar().getCurrentChargeValue();
                GameValue.player.getReceptionChar().setCurrentChargeValue(charge+1);
            }
        }else {
            if (GameValue.opponent.isAvailableA1()) {
                AttackHandle.elementAttack(leaderType, GameValue.opponent.getReceptionChar(), GameValue.player.getReceptionChar(), 1);
                int hydro = GameValue.dicesOfOpponent.getCryoDices();
                int count = GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setHydroDices(hydro - 1);
                GameValue.dicesOfOpponent.setDicesCount(count - 3);

                int charge = GameValue.opponent.getReceptionChar().getCurrentChargeValue();
                GameValue.opponent.getReceptionChar().setCurrentChargeValue(charge + 1);
            }
        }
    }

    /**
     * 水中幻愿
     * 元素战技
     * 造成1点水元素伤害，召唤虚影。
     */
    public static void charMonaE(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.isAvailableE()){
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(), 1);
                int times=GameValue.player.getVirtualShadowTimes();
                GameValue.player.setVirtualShadowTimes(times+1);

                int hydro=GameValue.dicesOfPlayer.getHydroDices();
                int count=GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setHydroDices(hydro-3);
                GameValue.dicesOfPlayer.setDicesCount(count-3);
                int charge=GameValue.player.getReceptionChar().getCurrentChargeValue();
                GameValue.player.getReceptionChar().setCurrentChargeValue(charge+1);

            }
        }else {
            if (GameValue.opponent.isAvailableE()) {
                AttackHandle.elementAttack(leaderType, GameValue.opponent.getReceptionChar(), GameValue.player.getReceptionChar(), 1);
                int times = GameValue.opponent.getVirtualShadowTimes();
                GameValue.opponent.setVirtualShadowTimes(times + 1);

                int hydro = GameValue.dicesOfOpponent.getHydroDices();
                int count = GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setHydroDices(hydro - 3);
                GameValue.dicesOfOpponent.setDicesCount(count - 3);
                int charge = GameValue.opponent.getReceptionChar().getCurrentChargeValue();
                GameValue.opponent.getReceptionChar().setCurrentChargeValue(charge + 1);
            }
        }
    }

    /**
     * 星命定轨
     * 元素爆发
     * 造成4水元素伤害，生成泡影。
     */
    public static void charManaQ(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.isAvailableQ()){
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(), 4);
                GameValue.player.setAliveBubble(true);

                int hydro=GameValue.dicesOfPlayer.getHydroDices();
                int count=GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setHydroDices(hydro-3);
                GameValue.dicesOfPlayer.setDicesCount(count-3);
                GameValue.player.getReceptionChar().setCurrentChargeValue(0);

            }
        }else {
            if(GameValue.opponent.isAvailableQ()) {
                AttackHandle.elementAttack(leaderType, GameValue.opponent.getReceptionChar(), GameValue.player.getReceptionChar(), 4);
                GameValue.opponent.setAliveBubble(true);

                int hydro = GameValue.dicesOfOpponent.getHydroDices();
                int count = GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setHydroDices(hydro - 3);
                GameValue.dicesOfOpponent.setDicesCount(count - 3);
                GameValue.opponent.getReceptionChar().setCurrentChargeValue(0);
            }
        }
    }


    /**行秋
     * 古华剑法
     * 普通攻击
     * 造成2点物理伤害。
     */
    public static void charXingQiuA(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if(GameValue.player.isAvailableA1()){
                AttackHandle.physicsAttack(GameValue.opponent.getReceptionChar(),2);
                int charge=GameValue.player.getReceptionChar().getCurrentChargeValue();
                GameValue.player.getReceptionChar().setCurrentChargeValue(charge+1);

                int hydro=GameValue.dicesOfPlayer.getHydroDices();
                int count=GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setHydroDices(hydro-1);
                GameValue.dicesOfPlayer.setDicesCount(count-3);

            }

        }else {
            if (GameValue.opponent.isAvailableA1()){
                AttackHandle.physicsAttack(GameValue.player.getReceptionChar(),2);
                int charge=GameValue.opponent.getReceptionChar().getCurrentChargeValue();
                GameValue.opponent.getReceptionChar().setCurrentChargeValue(charge+1);

                int hydro=GameValue.dicesOfOpponent.getHydroDices();
                int count=GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setHydroDices(hydro-1);
                GameValue.dicesOfOpponent.setDicesCount(count-3);
            }

        }
    }


    /**
     * 画雨笼山
     * 元素战技
     * 造成2点水元素伤害，本角色附着水元素，生成雨帘剑。
     */
    public static void charXingQiuE(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            if (GameValue.player.isAvailableE()){
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(), 2);

                GameValue.player.getReceptionChar().setElementType(ElementType.ELEM_HYDRO);

                int times=GameValue.player.getRainCurtainSwordTimes();
                GameValue.player.setRainCurtainSwordTimes(times+2);

                int hydro=GameValue.dicesOfPlayer.getHydroDices();
                GameValue.dicesOfPlayer.setHydroDices(hydro-3);
                int count=GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfPlayer.setDicesCount(count-3);

                int charge=GameValue.player.getReceptionChar().getCurrentChargeValue();
                GameValue.player.getReceptionChar().setCurrentChargeValue(charge+1);
            }
        }else {
            if (GameValue.opponent.isAvailableE()){
                AttackHandle.elementAttack(leaderType,GameValue.opponent.getReceptionChar(),GameValue.player.getReceptionChar(), 2);

                GameValue.opponent.getReceptionChar().setElementType(ElementType.ELEM_HYDRO);

                int times=GameValue.opponent.getRainCurtainSwordTimes();
                GameValue.opponent.setRainCurtainSwordTimes(times+2);

                int hydro=GameValue.dicesOfOpponent.getHydroDices();
                GameValue.dicesOfOpponent.setHydroDices(hydro-3);
                int count=GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setDicesCount(count-3);

                int charge=GameValue.opponent.getReceptionChar().getCurrentChargeValue();
                GameValue.opponent.getReceptionChar().setCurrentChargeValue(charge+1);
            }
        }
    }

    /**
     * 裁雨留虹
     * 元素爆发
     * 造成1点水元素伤害，本角色附着水元素，生成虹剑势。
     */
    public static void charXingQiuQ(LeaderType leaderType){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            if (GameValue.player.isAvailableQ()) {
                AttackHandle.elementAttack(leaderType,GameValue.player.getReceptionChar(),GameValue.opponent.getReceptionChar(), 1);

                GameValue.player.getReceptionChar().setElementType(ElementType.ELEM_HYDRO);

                int times=GameValue.player.getRainbowSwordPotentialTimes();
                GameValue.player.setRainbowSwordPotentialTimes(times+3);

                int hydro=GameValue.dicesOfPlayer.getHydroDices();
                int count=GameValue.dicesOfPlayer.getDicesCount();
                GameValue.dicesOfPlayer.setHydroDices(hydro-3);
                GameValue.dicesOfPlayer.setDicesCount(count-3);

                GameValue.player.getReceptionChar().setCurrentChargeValue(0);

            }
        }else {
            if (GameValue.opponent.isAvailableQ()){
                AttackHandle.elementAttack(leaderType,GameValue.opponent.getReceptionChar(),GameValue.player.getReceptionChar(), 1);

                GameValue.opponent.getReceptionChar().setElementType(ElementType.ELEM_HYDRO);

                int times=GameValue.opponent.getRainbowSwordPotentialTimes();
                GameValue.opponent.setRainbowSwordPotentialTimes(times+3);

                int hydro=GameValue.dicesOfOpponent.getHydroDices();
                int count=GameValue.dicesOfOpponent.getDicesCount();
                GameValue.dicesOfOpponent.setHydroDices(hydro-3);
                GameValue.dicesOfOpponent.setDicesCount(count-3);

                GameValue.player.getReceptionChar().setCurrentChargeValue(0);
            }
        }
    }
}
