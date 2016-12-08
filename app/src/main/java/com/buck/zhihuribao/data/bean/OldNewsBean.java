package com.buck.zhihuribao.data.bean;

import java.util.List;

/**
 * Created by Buck on 2016/12/8.
 */

public class OldNewsBean {

    /**
     * date : 20161207
     * stories : [{"images":["http://pic1.zhimg.com/c600a95dc2daf786bef0bc9deb5371d8.jpg"],"type":0,"id":9044986,"ga_prefix":"120722","title":"小事 · 我为什么要整容"},{"title":"吃丹麦曲奇的时候，应该配什么电影呢？","ga_prefix":"120721","images":["http://pic3.zhimg.com/c05afd93d51bed141bbada2b947ed712.jpg"],"multipic":true,"type":0,"id":9041970},{"images":["http://pic4.zhimg.com/b8d79ca4c569999cd8579a96d3c3bbc7.jpg"],"type":0,"id":9045297,"ga_prefix":"120720","title":"面对自闭症，我们需要帮助的不止是孩子，还有家长"},{"images":["http://pic1.zhimg.com/f1893a5f44163fc0df9b16ab5ab3f7e0.jpg"],"type":0,"id":9045287,"ga_prefix":"120719","title":"真要论辈分，天文学可比物理学高多了"},{"images":["http://pic4.zhimg.com/8345deda7378557ae97477dc72b9813b.jpg"],"type":0,"id":9045131,"ga_prefix":"120718","title":"骑着老爷车带着特制计数器，马拉松的距离就是这么测的"},{"images":["http://pic3.zhimg.com/879115001a6ef6ad3738deb95736901e.jpg"],"type":0,"id":9045243,"ga_prefix":"120717","title":"知乎好问题 · 内向的人如何处理社交焦虑？"},{"images":["http://pic1.zhimg.com/5528024f1309cfc72186724f9ef4b858.jpg"],"type":0,"id":9044167,"ga_prefix":"120716","title":"谣言满天飞的美国大选之后，聊聊媒体出了什么问题"},{"images":["http://pic4.zhimg.com/d4e314d92ae538ec7a2cdc58e2b6f83f.jpg"],"type":0,"id":9044680,"ga_prefix":"120715","title":"上班坐了一整天还觉得累，真不是我矫情"},{"title":"不知道名字怎么读的「夔龙系统」有多厉害？","ga_prefix":"120714","images":["http://pic1.zhimg.com/357fcd771e5817ad75527695985c79d8.jpg"],"multipic":true,"type":0,"id":9044642},{"images":["http://pic1.zhimg.com/de9c004a81046fca2d9b149c2cf6fccc.jpg"],"type":0,"id":9044656,"ga_prefix":"120713","title":"保监会派驻检查组，横扫 A 股的保险资本有麻烦了？"},{"images":["http://pic1.zhimg.com/9f06c93a1f3ffd96963d5cfe16ba1700.jpg"],"type":0,"id":9041984,"ga_prefix":"120712","title":"大误 · 来者，可是诸葛孔明？"},{"images":["http://pic2.zhimg.com/1443f49e428212a605c4bce58e56461d.jpg"],"type":0,"id":7329697,"ga_prefix":"120711","title":"「大轮船造好了，我们试试看会不会漏水吧」"},{"images":["http://pic2.zhimg.com/b1fe2ee22e51f86459c561bb30ddaa91.jpg"],"type":0,"id":9041200,"ga_prefix":"120710","title":"在意大利公投这事上，为什么主流观点又错了？"},{"images":["http://pic1.zhimg.com/490d161571d4df0578faec4ecb17f200.jpg"],"type":0,"id":9042321,"ga_prefix":"120709","title":"「大数据」和「深度学习」有什么区别？"},{"images":["http://pic1.zhimg.com/a88f899d38e3ceeef76c2c905c64aa28.jpg"],"type":0,"id":9040188,"ga_prefix":"120708","title":"为什么导体会导电？这是可以讲给初中生的版本"},{"images":["http://pic3.zhimg.com/0f15b81f496e9b9175b068ddca52b546.jpg"],"type":0,"id":9042634,"ga_prefix":"120707","title":"孩子有了「网瘾」，是家庭出了问题吗？"},{"title":"鬼知道这些年我吃过的都是什么玩意\u2026\u2026","ga_prefix":"120707","images":["http://pic3.zhimg.com/bbf555d5f117eb1fac4dbc1b54cd380e.jpg"],"multipic":true,"type":0,"id":9021508},{"images":["http://pic3.zhimg.com/921a5d7e4d1ced41afe8395494d56bca.jpg"],"type":0,"id":9042329,"ga_prefix":"120707","title":"被叫停的「万能险」究竟是什么？"},{"images":["http://pic1.zhimg.com/b8e168ae509818ddc9f5afdcba13a078.jpg"],"type":0,"id":9042974,"ga_prefix":"120707","title":"读读日报 24 小时热门 TOP 5 · 中国房东排名系统"},{"images":["http://pic3.zhimg.com/70b97a91771c26873d6e178719af1886.jpg"],"type":0,"id":9041301,"ga_prefix":"120706","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

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
}
