package org.qa_rewrite.model;

/**元素骰类
 * @author QA
 */
public class Dices implements StaticValue{

    /**相应的元素骰点数：
     * 风元素骰
     * 雷元素骰
     * 水元素骰
     * 火元素骰
     * 岩元素骰
     * 冰元素骰
     * 草元素骰
     * 万能元素骰
     * */
    private int anemoDices;
    private int electroDices;
    private int hydroDices;
    private int pyroDices;
    private int geoDices;
    private int cryoDices;
    private int dendroDices;
    private int universalDices;
    /**总的元素骰个数*/
    private int dicesCount;



    /**
     * 判断是否存在指定数量的相同元素的元素骰
     */
    private boolean isHaveSame;




    /**get、set方法*/
    public int getAnemoDices() {
        return anemoDices;
    }

    public void setAnemoDices(int anemoDices) {
        this.anemoDices = anemoDices;
    }

    public int getElectroDices() {
        return electroDices;
    }

    public void setElectroDices(int electroDices) {
        this.electroDices = electroDices;
    }

    public int getHydroDices() {
        return hydroDices;
    }

    public void setHydroDices(int hydroDices) {
        this.hydroDices = hydroDices;
    }

    public int getPyroDices() {
        return pyroDices;
    }

    public void setPyroDices(int pyroDices) {
        this.pyroDices = pyroDices;
    }

    public int getGeoDices() {
        return geoDices;
    }

    public void setGeoDices(int geoDices) {
        this.geoDices = geoDices;
    }

    public int getCryoDices() {
        return cryoDices;
    }

    public void setCryoDices(int cryoDices) {
        this.cryoDices = cryoDices;
    }

    public int getDendroDices() {
        return dendroDices;
    }

    public void setDendroDices(int dendroDices) {
        this.dendroDices = dendroDices;
    }

    public int getUniversalDices() {
        return universalDices;
    }

    public void setUniversalDices(int universalDices) {
        this.universalDices = universalDices;
    }

    public int getDicesCount() {
        return dicesCount;
    }

    public void setDicesCount(int dicesCount) {
        this.dicesCount = dicesCount;
    }

    /**判断是否存在指定数量的相同的元素骰*/
    public boolean isHaveSame(int n) {
        if (this.anemoDices==n||this.electroDices==n|| this.hydroDices==n
                ||this.pyroDices==n|| this.geoDices==n ||this.cryoDices==n
                ||this.dendroDices==n|| this.universalDices==n
                ||this.anemoDices+this.universalDices==n
                ||this.electroDices+this.universalDices==n
                ||this.hydroDices+this.universalDices==n
                ||this.pyroDices+this.universalDices==n
                ||this.geoDices+this.universalDices==n
                ||this.cryoDices+this.universalDices==n
                ||this.dendroDices+this.universalDices==n){
                    isHaveSame=true;
                }else {
                    isHaveSame=false;
                }
        return isHaveSame;
    }


}
