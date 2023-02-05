package org.qa_rewrite.controller;

import org.qa_rewrite.model.CharacterCard;
import org.qa_rewrite.model.Dices;
import org.qa_rewrite.model.GameValue;
import org.qa_rewrite.model.StaticValue;

/**
 * 判断角色的各项攻击是否满足发动条件，更新相应的Boolean变量
 * @author QA
 */
public class JudgeCharacterCardAvailable implements StaticValue{

    /**通过现有元素骰种类与个数与角色攻击所需的条件相对比，更新Boolean变量*/
    public void judgeCharAttackAvailable(LeaderType leaderType, Dices dices) {
        if (leaderType.equals(LeaderType.LEADER_PLAYER)) {
            /*如果该角色为甘雨（CHAR_GANYU）*/
            if (GameValue.player.getReceptionChar().getCharacterType().equals(StaticValue.CharacterType.CHAR_GANYU)) {
                /*普通攻击1需要1个冰元素骰和两个任意元素骰*/
                if (dices.getAnemoDices() +dices.getUniversalDices()>= 1 && dices.getDicesCount() - 1 >= 2) {
                    GameValue.player.setAvailableA1(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*普通攻击2需要5个冰元素骰*/
                if (dices.getCryoDices() +dices.getUniversalDices()>= 5) {
                    GameValue.player.setAvailableA2(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*元素战技需要3个冰元素骰*/
                if (dices.getCryoDices() +dices.getUniversalDices()>= 3) {
                    GameValue.player.setAvailableE(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*元素爆发需要3个冰元素骰和2点充能*/
                if (dices.getCryoDices() +dices.getUniversalDices()>= 3 && GameValue.player.getReceptionChar().getCurrentChargeValue() == 2) {
                    GameValue.player.setAvailableQ(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
            }

            /*如果该角色为莫娜（CHAR_MONA）*/
            if (GameValue.player.getReceptionChar().getCharacterType().equals(StaticValue.CharacterType.CHAR_MONA)) {
                /*普通攻击1需要1个水元素骰和两个任意元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 1 && dices.getDicesCount() - 1 >= 2) {
                    GameValue.player.setAvailableA1(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*元素战技需要3个水元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3) {
                    GameValue.player.setAvailableE(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*元素爆发需要3个水元素骰和3点充能*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3 && GameValue.player.getReceptionChar().getCurrentChargeValue() == 3) {
                    GameValue.player.setAvailableQ(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
            }

            /*如果该角色为行秋（CHAR_XINGQIU）*/
            if (GameValue.player.getReceptionChar().getCharacterType().equals(StaticValue.CharacterType.CHAR_XINGQIU)) {
                /*普通攻击1需要1个水元素骰和两个任意元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 1 && dices.getDicesCount() - 1 >= 2) {
                    GameValue.player.setAvailableA1(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*元素战技需要3个水元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3) {
                    GameValue.player.setAvailableE(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
                /*元素爆发需要3个水元素骰和2点充能*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3 && GameValue.player.getReceptionChar().getCurrentChargeValue() == 2) {
                    GameValue.player.setAvailableQ(true);
                }else {
                    GameValue.player.setAvailableA1(false);
                }
            }

        }else{
            /*如果该角色为甘雨（CHAR_GANYU）*/
            if (GameValue.opponent.getReceptionChar().getCharacterType().equals(StaticValue.CharacterType.CHAR_GANYU)) {
                /*普通攻击1需要1个冰元素骰和两个任意元素骰*/
                if (dices.getAnemoDices() +dices.getUniversalDices()>= 1 && dices.getDicesCount() - 1 >= 2) {
                    GameValue.opponent.setAvailableA1(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*普通攻击2需要5个冰元素骰*/
                if (dices.getCryoDices() +dices.getUniversalDices()>= 5) {
                    GameValue.opponent.setAvailableA2(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*元素战技需要3个冰元素骰*/
                if (dices.getCryoDices() +dices.getUniversalDices()>= 3) {
                    GameValue.opponent.setAvailableE(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*元素爆发需要3个冰元素骰和2点充能*/
                if (dices.getCryoDices() +dices.getUniversalDices()>= 3 && GameValue.opponent.getReceptionChar().getCurrentChargeValue() == 2) {
                    GameValue.opponent.setAvailableQ(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
            }

            /*如果该角色为莫娜（CHAR_MONA）*/
            if (GameValue.opponent.getReceptionChar().getCharacterType().equals(StaticValue.CharacterType.CHAR_MONA)) {
                /*普通攻击1需要1个水元素骰和两个任意元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 1 && dices.getDicesCount() - 1 >= 2) {
                    GameValue.opponent.setAvailableA1(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*元素战技需要3个水元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3) {
                    GameValue.opponent.setAvailableE(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*元素爆发需要3个水元素骰和3点充能*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3 && GameValue.opponent.getReceptionChar().getCurrentChargeValue() == 3) {
                    GameValue.opponent.setAvailableQ(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
            }

            /*如果该角色为行秋（CHAR_XINGQIU）*/
            if (GameValue.opponent.getReceptionChar().getCharacterType().equals(StaticValue.CharacterType.CHAR_XINGQIU)) {
                /*普通攻击1需要1个水元素骰和两个任意元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 1 && dices.getDicesCount() - 1 >= 2) {
                    GameValue.opponent.setAvailableA1(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*元素战技需要3个水元素骰*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3) {
                    GameValue.opponent.setAvailableE(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
                /*元素爆发需要3个水元素骰和2点充能*/
                if (dices.getHydroDices() +dices.getUniversalDices()>= 3 && GameValue.opponent.getReceptionChar().getCurrentChargeValue() == 2) {
                    GameValue.opponent.setAvailableQ(true);
                }else {
                    GameValue.opponent.setAvailableA1(false);
                }
            }
        }
    }
}
