package org.qa_rewrite.model;

/**
 * 角色牌类
 * @author QA
 */
public class CharacterCard extends Card implements StaticValue{


    /**角色牌对应人物角色
     * 元素类型
     * 武器类型
     * 所属阵营
     * */
    private CharacterType characterType;
    private ElementType elementType;
    private EquipmentType equipmentType;
    private CampType campType;

    /**
     * 角色最大充能点
     * 角色当前生命值
     * 角色当前充能点
     * 角色当前盾值
     */
    private int chargeValue;
    private int currentHpValue;
    private int currentChargeValue;
    private int currentShieldValue;



    /**
     * 造成的伤害值
     * 受到的伤害值
     */
    private int makeDamageValue;
    private int sufferDamageValue;








    /**标识角色是否可自由行动*/
    private boolean isFree;


    /**构造函数*/
    public CharacterCard() {
        /*初始化字段*/
        characterType= CharacterType.DEFAULT;
        elementType=ElementType.DEFAULT;
        equipmentType= EquipmentType.DEFAULT;
        campType= CampType.DEFAULT;
        chargeValue=0;
        currentHpValue=10;
        currentChargeValue=0;
        currentShieldValue=0;

        makeDamageValue=0;
        sufferDamageValue=0;



        isFree=true;

    }


    /**get、set方法*/
    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public CampType getCampType() {
        return campType;
    }

    public void setCampType(CampType campType) {
        this.campType = campType;
    }

    public int getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(int chargeValue) {
        this.chargeValue = chargeValue;
    }

    public int getCurrentHpValue() {
        return currentHpValue;
    }

    public void setCurrentHpValue(int currentHpValue) {
        if (currentHpValue<0){
            currentHpValue=0;
        }
        if(currentHpValue>=10){
            currentHpValue=10;
        }
        this.currentHpValue = currentHpValue;
    }

    public int getCurrentChargeValue() {
        return currentChargeValue;
    }

    public void setCurrentChargeValue(int currentChargeValue) {
        if(currentChargeValue<0){
            currentChargeValue=0;
        }
        if(currentChargeValue>=chargeValue){
            currentChargeValue=chargeValue;
        }
        this.currentChargeValue = currentChargeValue;
    }

    public int getCurrentShieldValue() {
        return currentShieldValue;
    }

    public void setCurrentShieldValue(int currentShieldValue) {
        if (currentShieldValue < 0) {
            currentShieldValue=0;
        }
        if (currentShieldValue > 2) {
            currentShieldValue=2;
        }
        this.currentShieldValue = currentShieldValue;
    }



    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getMakeDamageValue() {
        return makeDamageValue;
    }

    public void setMakeDamageValue(int makeDamageValue) {
        this.makeDamageValue = makeDamageValue;
    }

    public int getSufferDamageValue() {
        return sufferDamageValue;
    }

    public void setSufferDamageValue(int sufferDamageValue) {
        this.sufferDamageValue = sufferDamageValue;
    }
}
