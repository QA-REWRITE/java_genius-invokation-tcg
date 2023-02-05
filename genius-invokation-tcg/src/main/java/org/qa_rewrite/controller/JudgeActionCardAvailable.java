package org.qa_rewrite.controller;

import org.qa_rewrite.model.ActionCard;
import org.qa_rewrite.model.Dices;
import org.qa_rewrite.model.GameValue;
import org.qa_rewrite.model.StaticValue;

/**判断是否满足行动牌发动条件
 * @author QA
 */
public class JudgeActionCardAvailable implements StaticValue{
    /**行动牌*/
    public static void judgeEquipCardAvailable(LeaderType leaderType,ActionCard actCard, Dices dices){
        /*判断装备牌*/
        if(actCard.getCardType().equals(CardType.CARD_EQUIPMENT)){
            /*
              重帘留香
              装备牌
              天赋 战斗行动
             */
            if ("重帘留香".equals(actCard.getCardName())) {
                if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                    if (GameValue.player.getReceptionChar().getCharacterType().equals(CharacterType.CHAR_XINGQIU)&&dices.getHydroDices() + dices.getUniversalDices() >= 4) {
                        actCard.setAvailable(true);
                    } else {
                        actCard.setAvailable(false);
                    }
                }else {
                    if (GameValue.opponent.getReceptionChar().getCharacterType().equals(CharacterType.CHAR_XINGQIU)&&dices.getHydroDices() + dices.getUniversalDices() >= 4) {
                        actCard.setAvailable(true);
                    } else {
                        actCard.setAvailable(false);
                    }
                }
            }
            /*
             唯此一心
              装备牌
              天赋 战斗行动
             */
            if ("唯此一心".equals(actCard.getCardName())) {
                if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                    if (GameValue.player.getReceptionChar().getCharacterType().equals(CharacterType.CHAR_GANYU)&&dices.getCryoDices() + dices.getUniversalDices() >= 5) {
                        actCard.setAvailable(true);
                    } else {
                        actCard.setAvailable(false);
                    }
                }else {
                    if (GameValue.opponent.getReceptionChar().getCharacterType().equals(CharacterType.CHAR_GANYU)&&dices.getCryoDices() + dices.getUniversalDices() >= 5) {
                        actCard.setAvailable(true);
                    } else {
                        actCard.setAvailable(false);
                    }
                }
            }

            /*
              沉没的预言
              装备牌
              行动牌
              天赋 战斗行动
             */
            if ("沉没的预言".equals(actCard.getCardName())) {
                if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
                    if (dices.getHydroDices() + dices.getUniversalDices() >= 3&& GameValue.player.getReceptionChar().getCurrentChargeValue()==3) {
                        actCard.setAvailable(true);
                    } else {
                        actCard.setAvailable(false);
                    }
                }else {
                    if (dices.getHydroDices() + dices.getUniversalDices() >= 3&& GameValue.opponent.getReceptionChar().getCurrentChargeValue()==3) {
                        actCard.setAvailable(true);
                    } else {
                        actCard.setAvailable(false);
                    }
                }
            }

            /*
              祭礼弓
              装备牌
              行动牌
              武器 弓
             */
            if ("祭礼弓".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(3));
            }

            /*
              鸦羽弓
              装备牌
              武器 弓
             */
            if ("鸦羽弓".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(2));
            }

            /*
              祭礼剑
              装备牌
              武器 单手剑
             */
            if ("祭礼剑".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(3));
            }

            /*
              破冰踏雪的回音
              装备牌
              圣遗物
             */
            if ("破冰踏雪的回音".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(2));
            }

            /*
              赌徒的耳环
              装备牌
              圣遗物
             */
            if ("破冰踏雪的回音".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(1));
            }
        }

        /*事件牌*/
        if (actCard.getCardType().equals(CardType.CARD_EVENT)){
            /*
              元素共鸣：愈疗之水
              事件牌
              元素共鸣
             */
            if ("元素共鸣：愈疗之水".equals(actCard.getCardName())) {
                if (dices.getHydroDices()>=1||dices.getUniversalDices()>=1){
                    actCard.setAvailable(true);
                }else {
                    actCard.setAvailable(false);
                }
            }

            /*
              元素共鸣：交织之水
              事件牌
              元素共鸣
             */
            if ("元素共鸣：交织之水".equals(actCard.getCardName())){
                actCard.setAvailable(true);
            }

            /*
              星天之兆
              事件牌
             */
            if ("星天之兆".equals(actCard.getCardName())){
                if (dices.getDicesCount()>=2) {
                    actCard.setAvailable(true);
                }else {
                    actCard.setAvailable(false);
                }
            }

            /*
              一掷乾坤
              事件牌
             */
            if ("一掷乾坤".equals(actCard.getCardName())){
                actCard.setAvailable(true);
            }

            /*
              交给我吧！
              事件牌
             */
            if ("交给我吧！".equals(actCard.getCardName())){
                actCard.setAvailable(true);
            }

            /*
              莲花酥
              事件牌
              料理
             */
            if ("莲花酥".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(1));
            }

            /*
              兽肉薄荷卷
              事件牌
              料理
             */
            if ("兽肉薄荷卷".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(1));
            }

        }

        /*支援牌*/
        if (actCard.getCardType().equals(CardType.CARD_SUPPORT)){
            /*
              派蒙
              支援牌
              伙伴
             */
            if ("派蒙".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(3));
            }

            /*
              立本
              支援牌
              伙伴
             */
            if ("立本".equals(actCard.getCardName())) {
                actCard.setAvailable(true);
            }

            /*
              田铁嘴
              支援牌
              伙伴
             */
            if ("田铁嘴".equals(actCard.getCardName())) {
                if (dices.getDicesCount()>=2) {
                    actCard.setAvailable(true);
                }else {
                    actCard.setAvailable(false);
                }
            }

            /*
              璃月港口
              支援牌
              场地
             */
            if ("璃月港口".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(2));
            }

            /*
              西风大教堂
              支援牌
              场地
             */
            if ("西风大教堂".equals(actCard.getCardName())) {
                actCard.setAvailable(dices.isHaveSame(2));
            }
        }


    }

}
