package org.qa_rewrite.model;

/**
 * 卡牌基类
 * @author QA
 */
public class Card implements StaticValue{
    /**卡牌卡面图片文件名、卡牌名、卡牌故事、卡牌标签*/
    private String cardFileName;
    private String cardName;
    private String cardStory;
    private CardTag cardTag;

    /**构造函数*/
    public Card(){
        cardFileName="";
        cardName="";
        cardStory="";
        cardTag=CardTag.DEFAULT;

    }


    /**get、set方法*/
    public String getCardFileName() {
        return cardFileName;
    }

    public void setCardFileName(String cardFileName) {
        this.cardFileName = cardFileName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardStory() {
        return cardStory;
    }

    public void setCardStory(String cardStory) {
        this.cardStory = cardStory;
    }

    public CardTag getCardTag() {
        return cardTag;
    }

    public void setCardTag(CardTag cardTag) {
        this.cardTag = cardTag;
    }

}
