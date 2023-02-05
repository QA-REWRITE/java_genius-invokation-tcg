package org.qa_rewrite.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供常量，便于管理
 * @author QA
 */
public interface StaticValue {
    /**卡牌枚举类型*/
    enum CardType{
        /*角色牌、装备牌、事件牌、支援牌*/
        CARD_CHARACTER,
        CARD_EQUIPMENT,
        CARD_EVENT,
        CARD_SUPPORT,
        DEFAULT
    }


    /**卡牌所属标签枚举类型*/
    enum CardTag{
        /**武器、天赋、圣遗物、料理、道具、伙伴、场地、元素共鸣*/
        TAG_WEAPON,
        TAG_TALENT,
        TAG_ARTIFACT,
        TAG_FOOD,
        TAG_PROP,
        TAG_COMRADE,
        TAG_FIELD,
        TAG_ELEMENT_RESONANCE,
        TAG_OTHERS,
        DEFAULT
    }

    /**角色枚举类型*/
    enum CharacterType{
        /*甘雨、莫娜、行秋*/
        CHAR_GANYU,
        CHAR_MONA,
        CHAR_XINGQIU,
        DEFAULT
    }

    /**武器枚举类型*/
    enum EquipmentType{
        /*单手剑、双手剑、长柄武器、弓、法器*/
        EQUIP_SWORD,
        EQUIP_CLAYMORE,
        EQUIP_PLEARM,
        EQUIP_BOW,
        EQUIP_CATALYST,
        DEFAULT
    }

    /**元素枚举类型*/
    enum ElementType{
        /**风、雷、水、火、岩、冰、草*/
        ELEM_ANEMO,
        ELEM_ELECTRO,
        ELEM_HYDRO,
        ELEM_PYRO,
        ELEM_GEO,
        ELEM_CRYO,
        ELEM_DENDRO,
        DEFAULT
    }

    /**角色所属阵营*/
    enum CampType{
        /*璃月、蒙德、稻妻、须弥*/
        CAMP_LIYUE,
        CAMP_MONDSTADT,
        CAMP_LNAZUMA,
        CAMP_SUMERU,
        DEFAULT
    }


    /**行动发起者类型*/
    enum LeaderType{
        /*
        *玩家
        * 对手
         */
        LEADER_PLAYER,
        LEADER_OPPONENT
    }

    /**
     * 伤害类型
     */
    enum DamageType{
        /*
         * 物理伤害
         * 风元素伤害
         * 雷元素伤害
         * 水元素伤害
         * 火元素伤害
         * 岩元素伤害
         * 冰元素伤害
         * 草元素伤害
         */
        DAMAGE_PHYSICS,
        DAMAGE_ANEMO,
        DAMAGE_ELECTRO,
        DAMAGE_HYDRO,
        DAMAGE_PYRO,
        DAMAGE_GEO,
        DAMAGE_CRYO,
        DAMAGE_DENDRO,
    }


    /**行动类型*/
    enum ActionType{
        /**
         * 快速行动
         * 战斗行动
         */
        ACTION_QUICK,
        ACTION_COMBAT
    }

    /**角色牌牌库，包含所有的角色牌*/
    List<CharacterCard> CHARACTER_CARDS_LIBRARY_LIST = new ArrayList<>();
    /**行动牌牌库，包含所有的行动牌*/
    List<ActionCard> ACTION_CARDS_LIBRARY_LIST=new ArrayList<>();




    /**卡牌数据json文件 cardData.json文件路径*/
    String CARDS_DATA_FILE_PATH="src/main/resources/data/cardsData.json";


}
