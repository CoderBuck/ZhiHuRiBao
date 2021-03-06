package com.buck.zhihuribao.data.bean;

import java.util.List;

/**
 * Created by CoderBuck on 2016/11/21
 */


public class TodayNewsBean {

    /**
     * date : 20161121
     * stories : [{"images":["http://pic3.zhimg.com/827850266444dfd515b955f87aef029e.jpg"],"type":0,"id":8998817,"ga_prefix":"112119","title":"认识一个更加真实和好玩儿的非洲，从乌干达开始"},{"images":["http://pic4.zhimg.com/e418265b7c6d274e3e2a0cae271898fb.jpg"],"type":0,"id":8998233,"ga_prefix":"112118","title":"经常开车，总得掌握一些汽车故障的应急小技巧"},{"images":["http://pic2.zhimg.com/79ad7a8cd1fd737786e7a78cc0a8126d.jpg"],"type":0,"id":8998520,"ga_prefix":"112117","title":"知乎好问题 · 有氧运动要超过一定时间才有减脂作用吗？"},{"images":["http://pic3.zhimg.com/81c83339624777cc029ca016e301071a.jpg"],"type":0,"id":8998224,"ga_prefix":"112116","title":"巴菲特斥巨资购入航空股，虽然曾经吃过大亏"},{"images":["http://pic2.zhimg.com/3c1a97e7f43a0f3809a8d8436f976601.jpg"],"type":0,"id":8998008,"ga_prefix":"112115","title":"从孩子的角度，聊聊节目里的「实习爸爸」可能有什么影响"},{"images":["http://pic3.zhimg.com/e1abdf3737b980a2d3efe5c539811ff2.jpg"],"type":0,"id":8997469,"ga_prefix":"112114","title":"华为推荐的 Polar 码入选 5G 标准意味着什么？"},{"images":["http://pic1.zhimg.com/580831492b9895746ac52cdaffacc578.jpg"],"type":0,"id":8997436,"ga_prefix":"112113","title":"神舟十一号返回，航天员为什么没有自主出舱？"},{"images":["http://pic4.zhimg.com/b28b2be3d65be0769f7361c895e28837.jpg"],"type":0,"id":8997528,"ga_prefix":"112112","title":"大误 · 孟婆汤是什么味道的？"},{"images":["http://pic3.zhimg.com/4a1b4aaf55a53acc9d1d64c8870eaf02.jpg"],"type":0,"id":8995900,"ga_prefix":"112111","title":"不得不承认，有时候真的就是「运气问题」"},{"images":["http://pic4.zhimg.com/ac1f2ff3794bfaa924a323a9a8e97dcf.jpg"],"type":0,"id":8992610,"ga_prefix":"112110","title":"亲人刚刚被确诊了糖尿病，我能做些什么？"},{"images":["http://pic3.zhimg.com/74daa8db9912e469d9f2cade1b9994d2.jpg"],"type":0,"id":8984810,"ga_prefix":"112109","title":"就这样，大家都很理性，但歧视自发地产生了"},{"images":["http://pic1.zhimg.com/d45aa9cceeaf5e116ba997e41ff19538.jpg"],"type":0,"id":8996510,"ga_prefix":"112108","title":"刚入行时，我也担心「一不小心做了成功编剧怎么办？」"},{"images":["http://pic3.zhimg.com/45674d1487643238d3d1c1dac6f5f5c2.jpg"],"type":0,"id":8996342,"ga_prefix":"112107","title":"打制石器偷埋到古老地层里再假装发掘，这事儿真有人干过"},{"images":["http://pic3.zhimg.com/b39012cd5aff3e73cbb9b9a9fdf44d3a.jpg"],"type":0,"id":8993946,"ga_prefix":"112107","title":"为什么我煮的米饭不好吃？不都是放进电饭锅摁个开关吗？"},{"images":["http://pic3.zhimg.com/4b4e5a012ef4c5fcde8cf533c4abde7e.jpg"],"type":0,"id":8995955,"ga_prefix":"112107","title":"社交网络上的个性签名，其实是一场你来我往的暗战啊"},{"images":["http://pic2.zhimg.com/e292f582a3523b9a6986e150fe64d841.jpg"],"type":0,"id":8996298,"ga_prefix":"112107","title":"读读日报 24 小时热门 TOP 5 · 围观群众，误伤友军"},{"images":["http://pic2.zhimg.com/2428b9b240d2e0e5e446683c751fca45.jpg"],"type":0,"id":8996115,"ga_prefix":"112106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/ce435b27cf810d7c0cebc4dce87cc34a.jpg","type":0,"id":8998520,"ga_prefix":"112117","title":"知乎好问题 · 有氧运动要超过一定时间才有减脂作用吗？"},{"image":"http://pic2.zhimg.com/4097501370427d6eacd17888a3db9bed.jpg","type":0,"id":8998224,"ga_prefix":"112116","title":"巴菲特斥巨资购入航空股，虽然曾经吃过大亏"},{"image":"http://pic2.zhimg.com/e99d0c54dfef4a265e9e28a23b62741d.jpg","type":0,"id":8998008,"ga_prefix":"112115","title":"从孩子的角度，聊聊节目里的「实习爸爸」可能有什么影响"},{"image":"http://pic1.zhimg.com/80e53046d2334391d0f7d6db8be7e220.jpg","type":0,"id":8997469,"ga_prefix":"112114","title":"华为推荐的 Polar 码入选 5G 标准意味着什么？"},{"image":"http://pic4.zhimg.com/5798031c87a95280f7aab1343b21d60f.jpg","type":0,"id":8997436,"ga_prefix":"112113","title":"神舟十一号返回，航天员为什么没有自主出舱？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }



    public static class TopStoriesBean {
        /**
         * image : http://pic3.zhimg.com/ce435b27cf810d7c0cebc4dce87cc34a.jpg
         * type : 0
         * id : 8998520
         * ga_prefix : 112117
         * title : 知乎好问题 · 有氧运动要超过一定时间才有减脂作用吗？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
