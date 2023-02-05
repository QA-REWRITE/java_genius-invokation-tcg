package org.qa_rewrite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.qa_rewrite.model.ActionCard;
import org.qa_rewrite.model.CharacterCard;
import org.qa_rewrite.model.StaticValue;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 读取cardData.json文件，初始化牌库
 * @author QA
 */
public class InitCards implements StaticValue {

    /**读取json文件，返回字符串类型*/
    public static String readJsonFileToString(String fileName) {
        //读取json文件
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            StringBuilder strBuilder = new StringBuilder();
            int ch;
            while ((ch=reader.read()) != -1) {
                strBuilder.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return strBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**初始化牌库*/
    public static void initCardsLibrary() {
        String str = readJsonFileToString(CARDS_DATA_FILE_PATH);
        JSONObject jsonObject = JSON.parseObject(str);
        /*初始化角色牌牌库*/
        JSONArray charCards = jsonObject.getJSONArray("CharacterCards");
        for (Object card : charCards) {
            JSONObject key = (JSONObject) card;
            CharacterCard charCard = new CharacterCard();
            /*读取角色牌各项信息：
              卡牌卡面文件名
              卡牌名称
              卡牌故事
              角色类型
              元素类型
              武器类型
              所属阵营
              充能点数
             */
            charCard.setCardFileName((String) key.get("cardFileName"));
            charCard.setCardName((String) key.get("cardName"));
            charCard.setCardStory((String) key.get("cardStory"));
            charCard.setCharacterType(CharacterType.valueOf((String) key.get("characterType")));
            charCard.setElementType(ElementType.valueOf((String) key.get("elementType")));
            charCard.setEquipmentType(EquipmentType.valueOf((String) key.get("equipmentType")));
            charCard.setCampType(CampType.valueOf((String) key.get("campType")));
            charCard.setChargeValue((int) key.get("chargeValue"));

            /*添加到角色牌库*/
            CHARACTER_CARDS_LIBRARY_LIST.add(charCard);
        }

        /*初始化行动牌库*/
        JSONArray equipCards = jsonObject.getJSONArray("EquipmentCards");
        for (Object card : equipCards) {
            JSONObject key = (JSONObject) card;
            ActionCard equipCard = new ActionCard();
            /*
              读取行动牌（装备牌）信息：
              卡牌卡面文件名
              卡牌名称
              卡牌标签类型
              卡牌故事
              卡牌效果描述
              卡牌类型
             */
            equipCard.setCardFileName((String) key.get("cardFileName"));
            equipCard.setCardName((String) key.get("cardName"));
            equipCard.setCardTag(CardTag.valueOf((String) key.get("cardTag")));
            equipCard.setCardStory((String) key.get("cardStory"));
            equipCard.setEffectDescription((String) key.get("effectDescription"));
            equipCard.setCardType(CardType.valueOf((String) key.get("cardType")));

            /*添加到行动牌库。因为规则规定卡组中每张行动牌最多两张，所有添加两次*/
            ACTION_CARDS_LIBRARY_LIST.add(equipCard);
            ACTION_CARDS_LIBRARY_LIST.add(equipCard);
        }

        /*读取事件牌*/
        JSONArray eventCards = jsonObject.getJSONArray("EventCards");
        for (Object card : eventCards) {
            JSONObject key = (JSONObject) card;
            ActionCard eventCard = new ActionCard();
            /*
              读取行动牌（事件牌）信息：
              卡牌卡面文件名
              卡牌名称
              卡牌标签类型
              卡牌故事
              卡牌效果描述
              卡牌类型
             */
            eventCard.setCardFileName((String) key.get("cardFileName"));
            eventCard.setCardName((String) key.get("cardName"));
            eventCard.setCardTag(CardTag.valueOf((String) key.get("cardTag")));
            eventCard.setCardStory((String) key.get("cardStory"));
            eventCard.setEffectDescription((String) key.get("effectDescription"));
            eventCard.setCardType(CardType.valueOf((String) key.get("cardType")));

            /*添加到行动牌库。因为规则规定卡组中每张行动牌最多两张，所有添加两次*/
            ACTION_CARDS_LIBRARY_LIST.add(eventCard);
            ACTION_CARDS_LIBRARY_LIST.add(eventCard);
        }

        /*读取事件牌*/
        JSONArray suppCards = jsonObject.getJSONArray("SupportCards");
        for (Object card : suppCards) {
            JSONObject key = (JSONObject) card;
            ActionCard suppCard = new ActionCard();
            /*
              读取行动牌（支援牌）信息：
              卡牌卡面文件名
              卡牌名称
              卡牌标签类型
              卡牌故事
              卡牌效果描述
              卡牌类型
             */
            suppCard.setCardFileName((String) key.get("cardFileName"));
            suppCard.setCardName((String) key.get("cardName"));
            suppCard.setCardTag(CardTag.valueOf((String) key.get("cardTag")));
            suppCard.setCardStory((String) key.get("cardStory"));
            suppCard.setEffectDescription((String) key.get("effectDescription"));
            suppCard.setCardType(CardType.valueOf((String) key.get("cardType")));

            /*添加到行动牌库。因为规则规定卡组中每张行动牌最多两张，所有添加两次*/
            ACTION_CARDS_LIBRARY_LIST.add(suppCard);
            ACTION_CARDS_LIBRARY_LIST.add(suppCard);
        }
    }
}
