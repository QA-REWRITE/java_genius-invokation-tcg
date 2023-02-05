package org.qa_rewrite.model;

import java.util.ArrayList;
import java.util.List;

/**角色基类
 * @author QA
 */
public class Role implements StaticValue{



    /**角色牌组
     * 行动牌组
     * 前台出战角色
     * */
    private List<CharacterCard> characterCardsList;
    private List<ActionCard> actionCardsList;
    private CharacterCard receptionChar;

    /**元素反应生成物对应使用次数
     *[燃烧烈焰]
     * [草原核]
     * [激化领域]
     * */
    private int burningFlamesTimes;
    private int dendroCoreTimes;
    private int catalyzeAreasTimes;

    /**
     * 角色攻击生成物可用次数
     * 甘雨元素战技生成物：冰莲
     * 行秋元素战技生成物：雨帘剑
     * 行秋元素爆发生成物：虹剑势
     */
    private int iceLotusTimes;
    private int rainCurtainSwordTimes;
    private int rainbowSwordPotentialTimes;


    /**
     * 角色召唤物可用次数
     * 甘雨召唤物：冰灵珠
     * 莫娜元素战技召唤物：虚影
     */
    private int iceSoulPearlTimes;
    private int virtualShadowTimes;

    /**伤害类型*/
    private DamageType damageType;




    /**
     * 部分召唤物有弃置效果弃置
     * 莫娜：弃置召唤物虚影
     */
    private boolean isAbandonVirtualShadow;



    /**
     * 召唤物/生成物是否维持
     * 莫娜生成物：泡影
     */
    private boolean isAliveBubble;


    /**用于标识角色牌的各项技能是否满足发动条件
     * 普通攻击1
     * 普通攻击2（部分角色有）
     * 元素战技
     * 元素爆发
     * */
    private boolean isAvailableA1;
    private boolean isAvailableA2;
    private boolean isAvailableE;
    private boolean isAvailableQ;



    /**当前行动类型*/
    private ActionType actionType;

    /**构造函数*/
    public Role(){
        characterCardsList=new ArrayList<>();
        actionCardsList=new ArrayList<>();
        receptionChar=new CharacterCard();


        burningFlamesTimes=0;
        dendroCoreTimes=0;
        catalyzeAreasTimes=0;


        iceLotusTimes=0;
        rainCurtainSwordTimes=0;
        rainbowSwordPotentialTimes=0;

        iceSoulPearlTimes=0;
        virtualShadowTimes=0;


        isAbandonVirtualShadow=false;
        isAliveBubble=false;

        isAvailableA1=false;
        isAvailableA2=false;
        isAvailableE=false;
        isAvailableQ=false;
    }


    /*对局者操作方法*/
    /**
     * 切换前台出战角色
     */
    public static void switchReceptionChar(LeaderType leaderType,CharacterCard charCard){
        if (leaderType.equals(LeaderType.LEADER_PLAYER)){
            GameValue.player.setReceptionChar(charCard);
            GameValue.player.setActionType(ActionType.ACTION_COMBAT);
        }else{
            GameValue.opponent.setReceptionChar(charCard);
            GameValue.opponent.setActionType(ActionType.ACTION_COMBAT);
        }
    }




    /**get、set方法*/
    public List<CharacterCard> getCharacterCardsList() {
        return characterCardsList;
    }

    public void setCharacterCardsList(List<CharacterCard> characterCardsList) {
        this.characterCardsList = characterCardsList;
    }

    public List<ActionCard> getActionCardsList() {
        return actionCardsList;
    }

    public void setActionCardsList(List<ActionCard> actionCardsList) {
        this.actionCardsList = actionCardsList;
    }

    public CharacterCard getReceptionChar() {
        return receptionChar;
    }

    public void setReceptionChar(CharacterCard receptionChar) {
        this.receptionChar = receptionChar;
    }

    public int getBurningFlamesTimes() {
        return burningFlamesTimes;
    }

    public void setBurningFlamesTimes(int burningFlamesTimes) {
        this.burningFlamesTimes = burningFlamesTimes;
    }

    public int getDendroCoreTimes() {
        return dendroCoreTimes;
    }

    public void setDendroCoreTimes(int dendroCoreTimes) {
        this.dendroCoreTimes = dendroCoreTimes;
    }

    public int getCatalyzeAreasTimes() {
        return catalyzeAreasTimes;
    }

    public void setCatalyzeAreasTimes(int catalyzeAreasTimes) {
        this.catalyzeAreasTimes = catalyzeAreasTimes;
    }

    public int getIceLotusTimes() {
        return iceLotusTimes;
    }

    public void setIceLotusTimes(int iceLotusTimes) {
        this.iceLotusTimes = iceLotusTimes;
    }

    public int getIceSoulPearlTimes() {
        return iceSoulPearlTimes;
    }

    public void setIceSoulPearlTimes(int iceSoulPearlTimes) {
        this.iceSoulPearlTimes = iceSoulPearlTimes;
    }

    public int getVirtualShadowTimes() {
        return virtualShadowTimes;
    }

    public void setVirtualShadowTimes(int virtualShadowTimes) {
        this.virtualShadowTimes = virtualShadowTimes;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }


    public boolean isAbandonVirtualShadow() {
        return isAbandonVirtualShadow;
    }

    public void setAbandonVirtualShadow(boolean abandonVirtualShadow) {
        isAbandonVirtualShadow = abandonVirtualShadow;
    }

    public boolean isAliveBubble() {
        return isAliveBubble;
    }

    public void setAliveBubble(boolean aliveBubble) {
        isAliveBubble = aliveBubble;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public int getRainCurtainSwordTimes() {
        return rainCurtainSwordTimes;
    }

    public void setRainCurtainSwordTimes(int rainCurtainSwordTimes) {
        this.rainCurtainSwordTimes = rainCurtainSwordTimes;
    }

    public int getRainbowSwordPotentialTimes() {
        return rainbowSwordPotentialTimes;
    }

    public void setRainbowSwordPotentialTimes(int rainbowSwordPotentialTimes) {
        this.rainbowSwordPotentialTimes = rainbowSwordPotentialTimes;
    }

    public boolean isAvailableA1() {
        return isAvailableA1;
    }

    public void setAvailableA1(boolean availableA1) {
        isAvailableA1 = availableA1;
    }

    public boolean isAvailableA2() {
        return isAvailableA2;
    }

    public void setAvailableA2(boolean availableA2) {
        isAvailableA2 = availableA2;
    }

    public boolean isAvailableE() {
        return isAvailableE;
    }

    public void setAvailableE(boolean availableE) {
        isAvailableE = availableE;
    }

    public boolean isAvailableQ() {
        return isAvailableQ;
    }

    public void setAvailableQ(boolean availableQ) {
        isAvailableQ = availableQ;
    }


}
