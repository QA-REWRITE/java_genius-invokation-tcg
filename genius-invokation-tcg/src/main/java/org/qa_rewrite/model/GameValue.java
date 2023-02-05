package org.qa_rewrite.model;

/**
 * @author QA
 */
public class GameValue {
    /**
     * 玩家
     * 对手
     */
    public static Role player;
    public static Role opponent;

    /**
     * 元素骰所属：
     *玩家方的元素骰
     * 对手方的元素骰
     */
    public static Dices dicesOfPlayer;
    public static Dices dicesOfOpponent;

    /**一回合一次,在新回合开始时更新*/
    public static boolean aTimeInRound=true;



    /**构造函数*/
    public GameValue(){
        player = new Role();
        opponent = new Role();
    }
}
