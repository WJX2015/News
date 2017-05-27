package com.example.lenovo_g50_70.newspad.bean;

import java.util.List;

/**
 * 新闻信息的存储
 * Created by lenovo-G50-70 on 2017/5/16.
 */

public class News {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"05aca21643aef522f499b774c5ec28aa","title":"江苏省委原常委、秘书长赵少麟单位行贿、骗购外汇案一审宣判","date":"2017-05-18 15:46","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/170518154632454.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_3_mwpm_03200403.jpeg"}]}
     */

    private String reason;
    private ResultBean result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"05aca21643aef522f499b774c5ec28aa","title":"江苏省委原常委、秘书长赵少麟单位行贿、骗购外汇案一审宣判","date":"2017-05-18 15:46","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/170518154632454.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_3_mwpm_03200403.jpeg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 05aca21643aef522f499b774c5ec28aa
             * title : 江苏省委原常委、秘书长赵少麟单位行贿、骗购外汇案一审宣判
             * date : 2017-05-18 15:46
             * category : 头条
             * author_name : 央视网
             * url : http://mini.eastday.com/mobile/170518154632454.html
             * thumbnail_pic_s : http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_1_mwpm_03200403.jpeg
             * thumbnail_pic_s02 : http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_2_mwpm_03200403.jpeg
             * thumbnail_pic_s03 : http://00.imgmini.eastday.com/mobile/20170518/20170518154632_9182a4c3bbeaea3cd27604ab892368a0_3_mwpm_03200403.jpeg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
