package org.qa_rewrite.model;

/**
 * 行动牌
 * @author QA
 */
public class ActionCard extends Card implements StaticValue{


    /**行动牌类型：装备牌、事件牌、支援牌*/
    private CardType cardType;

    /**行动牌效果描述*/
    private String effectDescription;


    /**是否满足发动条件*/
    private boolean isAvailable;

    /**构造函数*/
    public ActionCard(){
        cardType=CardType.DEFAULT;
        effectDescription="";
        isAvailable=false;
    }

    /**get、set方法*/
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
