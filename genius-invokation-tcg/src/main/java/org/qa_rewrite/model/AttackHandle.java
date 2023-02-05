package org.qa_rewrite.model;

/**攻击处理类
 * 包含物理攻击处理，以及元素反应加成的处理
 * @author QA
 */
public class AttackHandle implements StaticValue{
    /**boolean 变量，用于防止无限发生【扩散】反应*/
    private static boolean isOneTime=true;

    /**物理攻击处理*/
    public static void physicsAttack(CharacterCard attackReceiver,int damage){

        int currShieldR=attackReceiver.getCurrentShieldValue();
        int currHp=attackReceiver.getCurrentHpValue();
        if(currShieldR-damage>0){
            currShieldR-=damage;
        }else {
            currShieldR=0;
            currHp=currHp+currShieldR-1;
        }
        attackReceiver.setCurrentShieldValue(currShieldR);
        attackReceiver.setCurrentHpValue(currHp);
        /*如果被攻击者此时被【冻结】中，受到物理伤害解除*/
        if (!attackReceiver.isFree()){
            attackReceiver.setFree(true);
        }
    }

    /**元素攻击,引发元素反应*/
    public static void elementAttack(LeaderType leaderType,CharacterCard attackSender,CharacterCard attackReceiver,int damage) {
        /*融化：冰+火
         *蒸发:火+水
         * 本伤害+2
         * */
        if ((attackSender.getElementType().equals(ElementType.ELEM_PYRO) && attackReceiver.getElementType().equals(ElementType.ELEM_CRYO))
                || (attackSender.getElementType().equals(ElementType.ELEM_CRYO) && attackReceiver.getElementType().equals(ElementType.ELEM_PYRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_PYRO) && attackReceiver.getElementType().equals(ElementType.ELEM_HYDRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_HYDRO) && attackReceiver.getElementType().equals(ElementType.ELEM_PYRO))) {
            damage += 2;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);
            /*被攻击者附着上与攻击者相同的元素*/
            attackReceiver.setElementType(attackSender.getElementType());
            /*若被攻击者此时处于【冻结】中。受到火伤解除*/
            if ((!attackReceiver.isFree()) && attackSender.getElementType().equals(ElementType.ELEM_PYRO)) {
                attackReceiver.setFree(true);
            }
        }

        /*超载:雷+火
         *本伤害+2；
         * 如果目标是「出战角色」，就强制切换到下一个角色
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_PYRO) && attackReceiver.getElementType().equals(ElementType.ELEM_ELECTRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ELECTRO) && attackReceiver.getElementType().equals(ElementType.ELEM_PYRO))) {
            damage += 2;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);

            /*被攻击者附着上与攻击者相同的元素*/
            attackReceiver.setElementType(attackSender.getElementType());
            /*若被攻击者此时处于【冻结】中。受到火伤解除*/
            if ((!attackReceiver.isFree()) && attackSender.getElementType().equals(ElementType.ELEM_PYRO)) {
                attackReceiver.setFree(true);
            }

            /*如果被攻击者为前台出战角色*/
            if (attackReceiver.equals(GameValue.opponent.getReceptionChar()) || attackReceiver.equals(GameValue.player.getReceptionChar())) {

                /*如果攻击者是玩家*/
                if (LeaderType.LEADER_PLAYER.equals(leaderType)) {
                    int i = 0;
                    /*若i号角色生命值已归零，则指定i+1号角色*/
                    if (GameValue.opponent.getCharacterCardsList().get(i).getCurrentHpValue() == 0) {
                        i++;
                    }
                    /*若i号角色正好是当前前台出战角色，则指定下一个角色*/
                    if (GameValue.opponent.getCharacterCardsList().get(i).equals(attackReceiver)) {
                        i++;
                        /*若i号角色生命值归零，则指定i+1号角色*/
                        if (GameValue.opponent.getCharacterCardsList().get(i).getCurrentHpValue() == 0) {
                            i++;
                            /*若i越界，则当前出战角色不变,即此时场上只有出战角色存活中*/
                            if (i > 2) {
                                i--;
                            }
                        }
                    }
                    GameValue.opponent.setReceptionChar(GameValue.opponent.getCharacterCardsList().get(i));
                }
                /*攻击者是对手*/
                else {
                    int i = 0;
                    /*若i号角色生命值已归零，则指定i+1号角色*/
                    if (GameValue.player.getCharacterCardsList().get(i).getCurrentHpValue() == 0) {
                        i++;
                    }
                    /*若i号角色正好是当前前台出战角色，则指定下一个角色*/
                    if (GameValue.player.getCharacterCardsList().get(i).equals(attackReceiver)) {
                        i++;
                        /*若i号角色生命值归零，则指定i+1号角色*/
                        if (GameValue.player.getCharacterCardsList().get(i).getCurrentHpValue() == 0) {
                            i++;
                            /*若i越界，则当前出战角色不变,即此时场上只有出战角色存活中*/
                            if (i > 2) {
                                i--;
                            }
                        }
                    }
                    GameValue.player.setReceptionChar(GameValue.player.getCharacterCardsList().get(i));
                }
            }


        }

        /*
         *超导:雷+冰
         * 感电：雷+水
         * 本伤害+1，对目标以外的所有敌方角色造成1点穿透伤害
         * （穿透伤害不受伤害加成，不会被抵消
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_ELECTRO) && attackReceiver.getElementType().equals(ElementType.ELEM_CRYO))
                || (attackSender.getElementType().equals(ElementType.ELEM_CRYO) && attackReceiver.getElementType().equals(ElementType.ELEM_ELECTRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ELECTRO) && attackReceiver.getElementType().equals(ElementType.ELEM_HYDRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_HYDRO) && attackReceiver.getElementType().equals(ElementType.ELEM_ELECTRO))) {
            damage += 1;

            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);

            attackReceiver.setElementType(attackSender.getElementType());

            /*如果攻击者为玩家*/
            if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                for (int i = 0; i < 3; i++) {
                    if (!GameValue.opponent.getCharacterCardsList().get(i).equals(attackReceiver)) {
                        int hp = GameValue.opponent.getCharacterCardsList().get(i).getCurrentHpValue();
                        GameValue.opponent.getCharacterCardsList().get(i).setCurrentHpValue(hp - 1);
                    }
                }
            }
            /*如果攻击者为对手*/
            if (leaderType.equals(LeaderType.LEADER_OPPONENT)) {
                for (int i = 0; i < 3; i++) {
                    if (!GameValue.player.getCharacterCardsList().get(i).equals(attackReceiver)) {
                        int hp = GameValue.player.getCharacterCardsList().get(i).getCurrentHpValue();
                        GameValue.player.getCharacterCardsList().get(i).setCurrentHpValue(hp - 1);
                    }
                }
            }
        }

        /*
         *冻结：冰+水
         * 本伤害+1，使目标角色附属[冻结]状态：
         * 角色无法使用技能（状态持续到回合结束为止）；
         * 角色受到火元素伤害或物理伤害时伤害值+2，并移除此状态。
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_CRYO) && attackReceiver.getElementType().equals(ElementType.ELEM_HYDRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_HYDRO) && attackReceiver.getElementType().equals(ElementType.ELEM_CRYO))) {
            damage += 1;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);
            /*元素附着*/
            attackReceiver.setElementType(attackSender.getElementType());
            /*冻结目标，限制行动*/
            attackReceiver.setFree(true);
        }

        /*
         *扩散:风分别与冰、水、火、雷
         * 对目标以外的所有敌方角色造成1点冰或水、或火或雷元素伤害
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_ANEMO) && attackReceiver.getElementType().equals(ElementType.ELEM_CRYO))
                || (attackSender.getElementType().equals(ElementType.ELEM_CRYO) && attackReceiver.getElementType().equals(ElementType.ELEM_ANEMO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ANEMO) && attackReceiver.getElementType().equals(ElementType.ELEM_HYDRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_HYDRO) && attackReceiver.getElementType().equals(ElementType.ELEM_ANEMO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ANEMO) && attackReceiver.getElementType().equals(ElementType.ELEM_ELECTRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ELECTRO) && attackReceiver.getElementType().equals(ElementType.ELEM_ANEMO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ANEMO) && attackReceiver.getElementType().equals(ElementType.ELEM_PYRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_PYRO) && attackReceiver.getElementType().equals(ElementType.ELEM_ANEMO))) {
            /*如果攻击者是玩家*/
            if (isOneTime && leaderType.equals(LeaderType.LEADER_PLAYER)) {
                for (int i = 0; i < 3; i++) {
                    if (!GameValue.opponent.getCharacterCardsList().get(i).equals(attackReceiver)) {
                        elementAttack(leaderType, attackSender, GameValue.opponent.getCharacterCardsList().get(i), 1);
                        GameValue.opponent.getCharacterCardsList().get(i).setElementType(attackSender.getElementType());

                        isOneTime = false;
                    }
                }
            }
        }

        /*
         *结晶：岩+冰（水、火、雷）
         * 本伤害+1，提供1点护盾，保护己方出战角色（护盾值可叠加，最多2点）
         * （护盾效果说明：所保护的角色受到伤害时，将会消耗「护盾值」来抵消伤害。）
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_GEO) && attackReceiver.getElementType().equals(ElementType.ELEM_CRYO))
                || (attackSender.getElementType().equals(ElementType.ELEM_CRYO) && attackReceiver.getElementType().equals(ElementType.ELEM_GEO))
                || (attackSender.getElementType().equals(ElementType.ELEM_GEO) && attackReceiver.getElementType().equals(ElementType.ELEM_HYDRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_HYDRO) && attackReceiver.getElementType().equals(ElementType.ELEM_GEO))
                || (attackSender.getElementType().equals(ElementType.ELEM_GEO) && attackReceiver.getElementType().equals(ElementType.ELEM_ELECTRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_ELECTRO) && attackReceiver.getElementType().equals(ElementType.ELEM_GEO))
                || (attackSender.getElementType().equals(ElementType.ELEM_GEO) && attackReceiver.getElementType().equals(ElementType.ELEM_PYRO))
                || (attackSender.getElementType().equals(ElementType.ELEM_PYRO) && attackReceiver.getElementType().equals(ElementType.ELEM_GEO))) {

            damage += 1;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);
            /*元素附着*/
            attackReceiver.setElementType(attackSender.getElementType());
            int currShield = attackSender.getCurrentShieldValue();
            attackSender.setCurrentShieldValue(currShield + 1);

        }

        /*
        *燃烧：草+火
        * 本伤害+1，生成[燃烧烈焰]：
        * 结束阶段：造成1点火元素伤害。（可用次数1，最多叠加到2）
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_DENDRO)&&attackReceiver.getElementType().equals(ElementType.ELEM_PYRO))
            ||(attackSender.getElementType().equals(ElementType.ELEM_PYRO)&&attackReceiver.getElementType().equals(ElementType.ELEM_DENDRO))){

            damage+=1;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);
            attackReceiver.setElementType(attackSender.getElementType());

            /*【燃耗烈焰】可用次数+1*/
            if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                int times = GameValue.player.getBurningFlamesTimes();
                GameValue.player.setBurningFlamesTimes(times + 1);
            }else{
                int times = GameValue.player.getBurningFlamesTimes();
                GameValue.opponent.setBurningFlamesTimes(times + 1);
            }
        }


        /*
         * 绽放：水+草
         * 本伤害+1，生成[草原核]：
         * 我方对敌方出战角色造成火元素伤害或雷元素伤害时，伤害值+2。（可用次数1）
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_HYDRO)&&attackReceiver.getElementType().equals(ElementType.ELEM_DENDRO))
                ||(attackSender.getElementType().equals(ElementType.ELEM_DENDRO)&&attackReceiver.getElementType().equals(ElementType.ELEM_HYDRO))){
            damage+=1;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);
            attackReceiver.setElementType(attackSender.getElementType());

            /*生成【草原核】*/
            if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                int times = GameValue.player.getDendroCoreTimes();
                GameValue.player.setDendroCoreTimes(times + 1);
            }else{
                int times = GameValue.player.getDendroCoreTimes();
                GameValue.opponent.setDendroCoreTimes(times + 1);
            }

        }

        /*
         * 原激化：雷+草
         * 本伤害+1，生成[激化领域]：
         * 我方对敌方出战角色造成雷元素伤害或草元素伤害时，伤害值+1。（可用次数2）（3.4将可用次数“3”改为“2”）
         */
        if ((attackSender.getElementType().equals(ElementType.ELEM_ELECTRO)&&attackReceiver.getElementType().equals(ElementType.ELEM_DENDRO))
                ||(attackSender.getElementType().equals(ElementType.ELEM_DENDRO)&&attackReceiver.getElementType().equals(ElementType.ELEM_ELECTRO))) {
            damage += 1;
            int currShieldR = attackReceiver.getCurrentShieldValue();
            int currHp = attackReceiver.getCurrentHpValue();
            if (currShieldR-damage>0){
                currShieldR-=damage;
            }else {
                currShieldR=0;
                currHp=currHp+currShieldR-damage;
            }

            attackReceiver.setCurrentHpValue(currHp);
            attackReceiver.setCurrentShieldValue(currShieldR);
            attackReceiver.setElementType(attackSender.getElementType());

            /*生成【激化领域】*/
            if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                int times = GameValue.player.getCatalyzeAreasTimes();
                GameValue.player.setCatalyzeAreasTimes(times + 2);
            }else{
                int times = GameValue.player.getCatalyzeAreasTimes();
                GameValue.opponent.setCatalyzeAreasTimes(times + 2);
            }
        }

    }
}
