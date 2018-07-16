package com.szmz.entity.response;

import com.szmz.entity.IEntity;

public class JZ_GetLocatinDetail_res extends BaseResponse<JZ_GetLocatinDetail_res.ResultBean>{


    public static class ResultBean implements IEntity{
        /**
         * familyid : 81fee15ed44642ca9c96b3ea70efaef1
         * idCard : 330922198904259577
         * signTime : 1525863168000
         * address : 河北省石家庄市桥西区新石中路(物联网大厦)
         * name : 钱俊郎
         * userId : 8a8a80236005ab66016005b4062d0026
         * userName : ywjd
         * dicId : 20203029
         * longitude : 114.44853
         * latitude : 38.02242
         */

        private String familyid;
        private String idCard;
        private long signTime;
        private String address;
        private String name;
        private String userId;
        private String userName;
        private String dicId;
        private String longitude;
        private String latitude;

        public String getFamilyid() {
            return familyid;
        }

        public void setFamilyid(String familyid) {
            this.familyid = familyid;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public long getSignTime() {
            return signTime;
        }

        public void setSignTime(long signTime) {
            this.signTime = signTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDicId() {
            return dicId;
        }

        public void setDicId(String dicId) {
            this.dicId = dicId;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
