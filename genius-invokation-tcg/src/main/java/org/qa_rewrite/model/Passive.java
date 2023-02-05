package org.qa_rewrite.model;

/**
 * 角色被动
 * @author QA
 */
public class Passive implements StaticValue{

    /**
     * 莫娜被动技能：虚实流动
     * 【被动】此角色为出战角色，我方执行「切换角色」行动时：将此次切换视为「快速行动」而非「战斗行动」。（每回合1次）
     * (说明：
     * 快速行动：执行了一次快速行动后，我方可以继续进行其他行动。只有执行了一次战斗行动后，才会轮到对方行动。
     * 战斗行动：我方执行了一次战斗行动后，会轮到对方行动。打出具有此规则的手牌是一个战斗行动，而非快速行动。
     */
    public static void virtualAndRealFlow(LeaderType leaderType){
        if(GameValue.aTimeInRound) {
            if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                GameValue.player.setActionType(ActionType.ACTION_QUICK);
                GameValue.aTimeInRound=false;
            } else {
                GameValue.opponent.setActionType(ActionType.ACTION_QUICK);
                GameValue.aTimeInRound=false;
            }
        }

    }
}
