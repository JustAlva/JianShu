package com.zkd.beans;

/**
* 关注 bean
* @author Alva
* create by 2017/1/18 14:34
*/
public class FollowPersonalBean {

    private int img_res;
    private String img_url;
    private String name;
    private boolean isUpdate;
    private int updateNum;
    private String introduce;

    /**
     *
     * @param img_res 头像图片资源
     * @param img_url 图片url
     * @param name  名称
     * @param isUpdate 是否更新
     * @param updateNum 更新数目
     * @param introduce 介绍
     */
    public FollowPersonalBean(int img_res, String img_url, String name, boolean isUpdate, int updateNum, String introduce) {
        this.img_res = img_res;
        this.img_url = img_url;
        this.name = name;
        this.isUpdate = isUpdate;
        this.updateNum = updateNum;
        this.introduce = introduce;
    }

    public FollowPersonalBean() {
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getImg_res() {

        return img_res;
    }

    public String getName() {
        return name;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public int getUpdateNum() {
        return updateNum;
    }

    public String getIntroduce() {
        return introduce;
    }
}
